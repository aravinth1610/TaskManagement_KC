package com.task.timesheet.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.task.modal.StandardTask;
import com.task.timesheet.requestPayload.TaskRequest;
import com.task.timesheet.responsePayload.StandardTaskResponse;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class TaskMapper {

	public abstract StandardTask standardTaskRequestMapper(TaskRequest taskRequest);

	public abstract StandardTask updateStandardTasktRequestMapper(TaskRequest taskRequest,@MappingTarget StandardTask standardTask);

	public abstract StandardTask deleteStandardTaskRequestMapper(Integer deleteFlag,@MappingTarget StandardTask standardTask);

	public abstract List<StandardTaskResponse> standardTaskModalAllMapper(List<StandardTask> standardTask);

	public abstract StandardTaskResponse standardTaskModalMapper(StandardTask standardTask);

	
}
