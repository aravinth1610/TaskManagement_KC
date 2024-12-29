package com.task.timesheet.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.task.timesheet.requestPayload.TimeSheetRequest;
import com.task.timesheet.services.TimeSheetServices;
import com.unicore.customeResponse.ResponseEntityWrapper;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/timesheet")
public class TimesheetController {

	private final TimeSheetServices timeSheetServices;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	private final ResponseEntityWrapper<?> createTimeSheet(@RequestBody @Valid TimeSheetRequest timeSheetRequest) {
		return new ResponseEntityWrapper<>("Success", timeSheetServices.createTimeSheet(timeSheetRequest),
				"Operation completed successfully.");
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{userId}")
	private final ResponseEntityWrapper<?> getTimeSheet(@PathVariable(name = "userId") Long userId) {
		return new ResponseEntityWrapper<>("Success", timeSheetServices.getTimeSheet(userId),
				"Operation completed successfully.");
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{userId}/{currentDate}")
	private final ResponseEntityWrapper<?> getTimeSheetByCurrentDate(@PathVariable(name = "userId") Long userId, @PathVariable(name = "currentDate") String currentDate,
			@RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size,
	        @RequestParam(defaultValue = "timeSheetUid") String sortBy,
	        @RequestParam(defaultValue = "asc") String sortDirection) {
		return new ResponseEntityWrapper<>("Success", timeSheetServices.getTimeSheetByDate(userId, currentDate, page, size, sortBy, sortDirection),
				"Operation completed successfully.");
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	private final ResponseEntityWrapper<?> getAllTimeSheet() {
		return new ResponseEntityWrapper<>("Success", timeSheetServices.getAllTimeSheet(),
				"Operation completed successfully.");
	}

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping("/{timeSheetUid}")
	private final ResponseEntityWrapper<?> patchTimeSheet(@RequestBody @Valid TimeSheetRequest timeSheetRequest,
			@PathVariable(name = "timeSheetUid") Long timeSheetUid) {
		return new ResponseEntityWrapper<>("Success", timeSheetServices.patchTimeSheet(timeSheetRequest, timeSheetUid),
				"Operation completed successfully.");
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{timeSheetUid}")
	private final void deleteTimeSheet(@PathVariable(name = "timeSheetUid") Long timeSheetUid) {
		timeSheetServices.deleteTimeSheet(timeSheetUid);
	}

}
