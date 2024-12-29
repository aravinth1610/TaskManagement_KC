package com.task.modal;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.task.enums.WorkType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "time_sheet")
public class TimeSheet extends AuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "timesheet_uid")
	private Long timeSheetUid;

	@Enumerated(value = EnumType.STRING)
	private WorkType workType;

	private String task;

	@Column(name = "task_description")
	private String taskDescription;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss")
	@Column(name = "start_date_time", updatable = false)
	private LocalDateTime startDateTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss")
	@Column(name = "end_date_time", updatable = false)
	private LocalDateTime endDateTime;

	private Double hours;

	public TimeSheet(Long timeSheetUid) {
		super();
		this.timeSheetUid = timeSheetUid;
	}

	@Override
	public int hashCode() {
		return Objects.hash(endDateTime, hours, startDateTime, task, taskDescription, timeSheetUid, workType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeSheet other = (TimeSheet) obj;
		return Objects.equals(endDateTime, other.endDateTime) && Objects.equals(hours, other.hours)
				&& Objects.equals(startDateTime, other.startDateTime) && Objects.equals(task, other.task)
				&& Objects.equals(taskDescription, other.taskDescription)
				&& Objects.equals(timeSheetUid, other.timeSheetUid) && workType == other.workType;
	}

}
