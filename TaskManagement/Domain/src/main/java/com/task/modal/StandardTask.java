package com.task.modal;

import java.util.Date;
import java.util.Objects;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "standard_task")
public class StandardTask extends AuditEntity {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "task_uid")
	private Long tasKUid;
	
	@Column (name = "task_title")
	private String taskTitle;

	private String product;

	private String client;

	@Column (name = "assign_to")
	private Long assignTo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date_time")
	private Date startDateTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date_time")
	private Date endDateTime;

	private Double hours;

	@Override
	public int hashCode() {
		return Objects.hash(assignTo, client, endDateTime, hours, product, startDateTime, tasKUid, taskTitle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StandardTask other = (StandardTask) obj;
		return Objects.equals(assignTo, other.assignTo) && Objects.equals(client, other.client)
				&& Objects.equals(endDateTime, other.endDateTime) && Objects.equals(hours, other.hours)
				&& Objects.equals(product, other.product) && Objects.equals(startDateTime, other.startDateTime)
				&& Objects.equals(tasKUid, other.tasKUid) && Objects.equals(taskTitle, other.taskTitle);
	}
	
	
}
