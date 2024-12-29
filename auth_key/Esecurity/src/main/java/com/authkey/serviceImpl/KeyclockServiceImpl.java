package com.authkey.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.common.util.CollectionUtil;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.ProtocolMapperRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import com.authkey.keyClockUtils.SecurityUntil;
import com.authkey.payload.response.Role;
import com.authkey.payload.response.User;
import com.authkey.service.KeyclockService;

@Server
public class KeyclockServiceImpl implements KeyclockService {

	private final SecurityUntil securityUntil;

	private Keycloak keycloakUtil;

	private String realm;

	public KeyclockServiceImpl(SecurityUntil securityUntil) {
		super();
		this.securityUntil = securityUntil;
	}

	public void init(String realm, String clientId) {
		this.keycloakUtil = securityUntil.getKeyCloakInstance(realm, clientId);
		this.realm = realm;
	}

	public List<User> getMethodName() {
		List<UserRepresentation> userRep = keycloakUtil.realm(realm).users().list();
		return mapUsers(userRep);
	}

	public User getMethodUserID(String id) {
		UserRepresentation userRep = keycloakUtil.realm(realm).users().get(id).toRepresentation();
		System.out.println(userRep);
		return mapUser(userRep);
	}

	public User createUser(User user) {
		UserRepresentation userRep = mapUserRep(user);
		keycloakUtil.realm(realm).users().create(userRep);
		return user;
	}

	public User putUser(User User) {
		UserRepresentation userRep = mapUserRep(User);
		keycloakUtil.realm(realm).users().get(User.getId()).update(userRep);
		return User;
	}

	public void deleteMethodUserID(String id) {
		keycloakUtil.realm(realm).users().delete(id);
	}

	public List<Role> getMethodListRoles() {
		List<RoleRepresentation> userRep = keycloakUtil.realm(realm).roles().list();
		return mapRoles(userRep);
	}

	public Role createRole(Role role) {
		RoleRepresentation userRep = mapRoleRep(role);
		keycloakUtil.realm(realm).roles().create(userRep);
		return role;
	}

	public Role getMethodByRolesName(String rolename) {
		RoleRepresentation roleRep = keycloakUtil.realm(realm).roles().get(rolename).toRepresentation();
		System.out.println(roleRep);
		return mapRole(roleRep);
	}

	// Setting CLAIMS
	public void SettingMapperAndAttribute(String id) {
		addClientUsersMapper(id);
	}

	public List<Role> mapRoles(List<RoleRepresentation> roleRep) {

		List<Role> roles = new ArrayList<Role>();
		if (CollectionUtil.isNotEmpty(roleRep)) {
			roleRep.forEach(role -> {
				roles.add(mapRole(role));
			});
		}
		return roles;
	}

	public Role mapRole(RoleRepresentation roleRep) {
		Role role = new Role();
		role.setId(roleRep.getId());
		role.setName(roleRep.getName());
		return role;
	}

	public RoleRepresentation mapRoleRep(Role role) {
		RoleRepresentation roleRep = new RoleRepresentation();
		roleRep.setName(role.getName());
		return roleRep;
	}

	public List<User> mapUsers(List<UserRepresentation> userRep) {

		List<User> users = new ArrayList<User>();
		if (CollectionUtil.isNotEmpty(userRep)) {
			userRep.forEach(user -> {
				users.add(mapUser(user));
			});
		}
		return users;
	}

	public User mapUser(UserRepresentation userRep) {
		User user = new User();
		user.setId(userRep.getId());
		user.setFirstName(userRep.getFirstName());
		user.setLastName(userRep.getLastName());
		user.setEmail(userRep.getEmail());
		user.setUserName(userRep.getUsername());
		return user;
	}

	public UserRepresentation mapUserRep(User user) {

		UserRepresentation userRep = new UserRepresentation();
		userRep.setFirstName(user.getFirstName());
		userRep.setLastName(user.getLastName());
		userRep.setUsername(user.getUserName());
		userRep.setEmail(user.getEmail());
		userRep.setEmailVerified(true);
		userRep.setEnabled(true); // enable the user
		List<CredentialRepresentation> Listcred = new ArrayList<CredentialRepresentation>();
		CredentialRepresentation cred = new CredentialRepresentation();
		cred.setTemporary(false);
		cred.setValue(user.getPassword());
		Listcred.add(cred);
		userRep.setCredentials(Listcred);
		return userRep;
	}

	public void addClientUsersMapper(String userId) {
		RealmResource realmResource = keycloakUtil.realm(realm);
		ClientRepresentation client = realmResource.clients().findByClientId("springboot-dev").get(0);

		// Define a new mapper
		ProtocolMapperRepresentation mapper = new ProtocolMapperRepresentation();

		System.out.println(mapper.getName());
		mapper.setName("adminMapper"); // Mapper name
		mapper.setProtocol("openid-connect"); // Protocol
		mapper.setProtocolMapper("oidc-usermodel-attribute-mapper"); // Mapper type
		// Set the configuration for the mapper
		Map<String, String> config = new HashMap<>();
		config.put("user.attribute", "adminF");
		config.put("claim.name", "adminF"); // Set the token claim name
		config.put("access.token.claim", "true"); // Set to add to access token

		mapper.setConfig(config);

		// Add the mapper to the client
		realmResource.clients().get(client.getId()).getProtocolMappers().createMapper(mapper);

		// Single User
		UsersResource usersResource = realmResource.users();
		UserResource userResource = usersResource.get(userId);
		UserRepresentation userRepresentation = userResource.toRepresentation();
		// Set the custom attribute value for the user
		// We can also Add Object to this.
		userRepresentation.getAttributes().put("adminF", Collections.singletonList("what need to do here"));
		userResource.update(userRepresentation);
		
	}

	/*
	 * Multiple User // Get the list of users UsersResource usersResource =
	 * realmResource.users();
	 * 
	 * // Iterate over users and set the custom attribute
	 * usersResource.list().forEach(user -> { UserResource userResource =
	 * usersResource.get(user.getId()); UserRepresentation userRepresentation =
	 * userResource.toRepresentation(); // Check if attributes map is null,
	 * initialize it if necessary if (userRepresentation.getAttributes() == null) {
	 * userRepresentation.setAttributes(new HashMap<>()); }
	 * userRepresentation.getAttributes().put("employeeIdF",
	 * Collections.singletonList("12345")); userResource.update(userRepresentation);
	 * });
	 */

}
