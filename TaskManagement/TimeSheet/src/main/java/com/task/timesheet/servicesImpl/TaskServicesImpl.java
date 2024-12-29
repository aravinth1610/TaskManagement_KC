package com.task.timesheet.servicesImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.task.modal.StandardTask;
import com.task.repositories.StandardTaskRepositories;
import com.task.timesheet.mapper.TaskMapper;
import com.task.timesheet.requestPayload.TaskRequest;
import com.task.timesheet.responsePayload.StandardTaskResponse;
import com.task.timesheet.services.TaskServices;
import com.unicore.customeExceptions.CommonCaseException;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class TaskServicesImpl implements TaskServices {

	private final TaskMapper taskMapper;
	private final StandardTaskRepositories standardTaskRepository;

	public StandardTaskResponse createTask(TaskRequest taskRequest) {
		StandardTask task = taskMapper.standardTaskRequestMapper(taskRequest);
		return taskMapper.standardTaskModalMapper(standardTaskRepository.save(task));
	}

	public Set<StandardTaskResponse> getAllStandardTask() {
		return new HashSet<StandardTaskResponse>(
				taskMapper.standardTaskModalAllMapper(standardTaskRepository.findAll()));
	}

	public StandardTaskResponse getStandardTask(Long userId) {
		StandardTask task = standardTaskRepository.findByUserId(userId)
				.orElseThrow(() -> new CommonCaseException("TimeSheetUid " + userId + " not found."));
		return taskMapper.standardTaskModalMapper(task);
	}

	public StandardTaskResponse patchStandardTask(TaskRequest taskRequest, Long taskUid) {
		taskRequest.setTaskUid(taskUid);

		StandardTask task = taskMapper.updateStandardTasktRequestMapper(taskRequest,
				standardTaskRepository.findById(taskUid).get());
		return taskMapper.standardTaskModalMapper(task);

	}

	public void deleteTimeSheet(Long taskUid) {
		StandardTask task = taskMapper.deleteStandardTaskRequestMapper(1,
				standardTaskRepository.findById(taskUid).get());
		standardTaskRepository.save(task);
	}

}
