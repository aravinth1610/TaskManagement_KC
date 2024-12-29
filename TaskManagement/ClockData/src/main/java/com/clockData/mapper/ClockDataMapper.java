package com.clockData.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

import com.clockData.responsePayload.TimeSheetResponse;
import com.task.modal.TimeSheet;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class ClockDataMapper {

	public abstract Set<TimeSheetResponse> timeSheetModalAllMapper(Page<TimeSheet> timeSheet);
	
}
