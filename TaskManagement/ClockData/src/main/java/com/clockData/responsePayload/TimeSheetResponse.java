package com.clockData.responsePayload;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.task.enums.WorkType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TimeSheetResponse {

	private Long timeSheetUid;
	
	private Long userId;

	private WorkType workType;

	private String task;

	private String taskDescription;

	private LocalDateTime startDateTime;

	private LocalDateTime endDateTime;

	private Double hours;

}
