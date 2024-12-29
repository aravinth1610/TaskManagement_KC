package com.task.timesheet.mapper;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

import com.task.modal.TimeSheet;
import com.task.timesheet.requestPayload.TimeSheetRequest;
import com.task.timesheet.responsePayload.TimeSheetResponse;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class TimeSheetMapper {

	public abstract TimeSheet timeSheetRequestMapper(TimeSheetRequest timeSheetRequest);

	public abstract TimeSheet updateTimeSheetRequestMapper(TimeSheetRequest timeSheetRequest,@MappingTarget TimeSheet timeSheet);

	public abstract TimeSheet deleteTimeSheetRequestMapper(Integer deleteFlag,@MappingTarget TimeSheet timeSheet);

	public abstract List<TimeSheetResponse> timeSheetModalAllMapper(List<TimeSheet> timeSheet);

	public abstract TimeSheetResponse timeSheetModalMapper(TimeSheet timeSheet);
	
	public abstract Set<TimeSheetResponse> timeSheetModalAllMapper(Page<TimeSheet> timeSheet);

}
