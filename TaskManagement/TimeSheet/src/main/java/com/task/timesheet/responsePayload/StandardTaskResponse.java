package com.task.timesheet.responsePayload;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StandardTaskResponse {

	private Long tasKUid;
	
	private Long userId;

	private String taskTitle;

	private String product;

	private String client;

	private Long assignTo;

	private Date startDateTime;

	private Date endDateTime;

	private Double hours;

}
