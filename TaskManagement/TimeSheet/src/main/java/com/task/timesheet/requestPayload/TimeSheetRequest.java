package com.task.timesheet.requestPayload;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSheetRequest {

	public interface OnCreate {
	}

	public interface OnUpdate {
	}

	@NotEmpty(groups = { OnCreate.class })
	@NotNull(groups = { OnCreate.class })
	private Long timeSheetUid;

	private String workType;

	private String task;

	private String taskDescription;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss")
	private LocalDateTime startDateTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss")
	private LocalDateTime endDateTime;

	private Double hours;

}
