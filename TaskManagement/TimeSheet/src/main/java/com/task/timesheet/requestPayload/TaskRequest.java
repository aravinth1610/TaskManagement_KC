package com.task.timesheet.requestPayload;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.task.timesheet.requestPayload.TimeSheetRequest.OnCreate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {

	public interface OnCreate {
	}

	public interface OnUpdate {
	}

	@NotEmpty(groups = { OnCreate.class })
	@NotNull(groups = { OnCreate.class })
	private Long taskUid;

	private String taskTitle;

	private String product;

	private String client;

	private Long assignTo;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss")
	private Date startDateTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss")
	private Date endDateTime;

	private Double hours;

}
