package com.clockData.services;

import java.util.Set;

import com.clockData.responsePayload.TimeSheetResponse;

public interface ClockDataService {

	Set<TimeSheetResponse> getTimeSheetByDate(Long userId, String startDate, String endDate, int page, int size, String sortBy, String sortDirection);
}
