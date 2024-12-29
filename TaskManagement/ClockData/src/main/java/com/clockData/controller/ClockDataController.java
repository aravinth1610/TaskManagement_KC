package com.clockData.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.clockData.services.ClockDataService;
import com.unicore.customeResponse.ResponseEntityWrapper;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/clock")
public class ClockDataController {

	private final ClockDataService clockDataService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{userId}")
	private final ResponseEntityWrapper<?> getTimeSheetByCurrentDate(@PathVariable(name = "userId") Long userId,
			@RequestParam(name = "startDate") String startDate, @RequestParam(name = "endDate") String endDate,
			@RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size,
	        @RequestParam(defaultValue = "timeSheetUid") String sortBy,
	        @RequestParam(defaultValue = "asc") String sortDirection) {
		return new ResponseEntityWrapper<>("Success", clockDataService.getTimeSheetByDate(userId, startDate, endDate, page, size, sortBy, sortDirection),
				"Operation completed successfully.");
	}

}
