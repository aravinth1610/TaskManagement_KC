package com.task.timesheet.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.task.timesheet.requestPayload.TaskRequest;
import com.task.timesheet.services.TaskServices;
import com.unicore.customeResponse.ResponseEntityWrapper;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/task")
public class TaskController {
	
	private final TaskServices taskServices;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	private final ResponseEntityWrapper<?> createTask(@RequestBody @Valid TaskRequest taskRequest) {
		return new ResponseEntityWrapper<>("Success",taskServices.createTask(taskRequest), "Operation completed successfully.");
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{userId}")
	private final ResponseEntityWrapper<?> getTask(@PathVariable(name = "userId") Long userId) {
		return new ResponseEntityWrapper<>("Success",taskServices.getStandardTask(userId), "Operation completed successfully.");
	}

	private final void getAssignToByProductAndClient() {

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	private final ResponseEntityWrapper<?> getAllTimeSheet() {
		return new ResponseEntityWrapper<>("Success",taskServices.getAllStandardTask(), "Operation completed successfully.");
	}

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping("/{taskUid}")
	private final ResponseEntityWrapper<?> patchTimeSheet(@RequestBody @Valid TaskRequest taskRequest, @PathVariable(name = "taskUid") Long taskUid) {
		return new ResponseEntityWrapper<>("Success",taskServices.patchStandardTask(taskRequest, taskUid), "Operation completed successfully.");
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{taskUid}")
	private final void deleteTimeSheet(@PathVariable(name = "taskUid") Long taskUid) {
		taskServices.deleteTimeSheet(taskUid);
	}

	private final void usersDropDown() {
		
	}
	
}
