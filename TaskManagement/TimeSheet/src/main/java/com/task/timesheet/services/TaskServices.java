package com.task.timesheet.services;

import java.util.Set;

import com.task.timesheet.requestPayload.TaskRequest;
import com.task.timesheet.responsePayload.StandardTaskResponse;

public interface TaskServices {

	StandardTaskResponse createTask(TaskRequest taskRequest);
	
	Set<StandardTaskResponse> getAllStandardTask();
	
	StandardTaskResponse getStandardTask(Long userId);
	
	StandardTaskResponse patchStandardTask(TaskRequest taskRequest, Long taskUid);
	
	void deleteTimeSheet(Long taskUid);
}
