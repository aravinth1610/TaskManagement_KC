package com.clockData.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.clockData.mapper.ClockDataMapper;
import com.clockData.responsePayload.TimeSheetResponse;
import com.clockData.services.ClockDataService;
import com.task.modal.TimeSheet;
import com.task.repositories.TimeSheetRepositories;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ClockDataServiceImpl implements ClockDataService {

	private final TimeSheetRepositories timeSheetRepository;

	private final ClockDataMapper clockDataMapper;
	
	public Set<TimeSheetResponse> getTimeSheetByDate(Long userId, String startDate, String endDate, int page, int size, String sortBy, String sortDirection) {
		Page<TimeSheet> timeSheet = timeSheetRepository.findAllByTimeSheetUidOrStartAndEndDateTime(userId, stringToDateConverter(startDate), stringToDateConverter(endDate), pageable(page, size, sortBy, sortDirection));
		return clockDataMapper.timeSheetModalAllMapper(timeSheet);
	}
	
	private LocalDateTime stringToDateConverter(String date) {
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		System.out.println(LocalDate.parse(date, inputFormatter));
		return LocalDate.parse(date, inputFormatter).atStartOfDay();
	}

	private Pageable pageable(int page, int size, String sortBy, String sortDirection) {
	    Sort sort = sortDirection.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
	    return PageRequest.of(page, size, sort);
	}

	
}
