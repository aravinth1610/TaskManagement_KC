package com.task.timesheet.services;

import java.util.List;
import java.util.Set;

import com.task.timesheet.requestPayload.TimeSheetRequest;
import com.task.timesheet.responsePayload.TimeSheetResponse;

public interface TimeSheetServices {

	TimeSheetResponse createTimeSheet(TimeSheetRequest timeSheetRequest);

	List<TimeSheetResponse> getAllTimeSheet();

	TimeSheetResponse getTimeSheet(Long userId);
	
	Set<TimeSheetResponse> getTimeSheetByDate(Long userId,String startDate, int page, int size, String sortBy, String sortDirection);
	
	TimeSheetResponse patchTimeSheet(TimeSheetRequest timeSheetRequest, Long userId);
	
	void deleteTimeSheet(Long userId);

}
