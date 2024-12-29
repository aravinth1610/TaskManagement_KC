package com.task.timesheet.servicesImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.modal.TimeSheet;
import com.task.repositories.TimeSheetRepositories;
import com.task.timesheet.mapper.TimeSheetMapper;
import com.task.timesheet.requestPayload.TimeSheetRequest;
import com.task.timesheet.responsePayload.TimeSheetResponse;
import com.task.timesheet.services.TimeSheetServices;
import com.unicore.customeExceptions.CommonCaseException;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class TimeSheetServicesImpl implements TimeSheetServices {

	private final TimeSheetMapper timeSheetMapper;

	private final TimeSheetRepositories timeSheetRepository;
	
	public TimeSheetResponse createTimeSheet(TimeSheetRequest timeSheetRequest) {
		TimeSheet timeSheet = timeSheetMapper.timeSheetRequestMapper(timeSheetRequest);

		return timeSheetMapper.timeSheetModalMapper(timeSheetRepository.save(timeSheet));
	}

	public List<TimeSheetResponse> getAllTimeSheet() {
		return timeSheetMapper.timeSheetModalAllMapper(timeSheetRepository.findAll());
	}

	public TimeSheetResponse getTimeSheet(Long userId) {
		TimeSheet timeSheet = timeSheetRepository.findByTimeSheetUidOrStartAndEndDateTime(userId)
				.orElseThrow(() -> new CommonCaseException("TimeSheetUid " + userId + " not found."));
		return timeSheetMapper.timeSheetModalMapper(timeSheet);
	}

	public Set<TimeSheetResponse> getTimeSheetByDate(Long userId, String startDate, int page, int size, String sortBy, String sortDirection) {
		LocalDateTime startOfDay = stringToDateConverter(startDate);
		LocalDateTime endOfDay = stringToDateConverter(startDate).plusDays(1);
		Page<TimeSheet> timeSheet = timeSheetRepository.findAllByTimeSheetUidOrStartAndEndDateTime(userId, startOfDay, endOfDay, pageable(page, size, sortBy, sortDirection));
		return timeSheetMapper.timeSheetModalAllMapper(timeSheet);
	}

	public TimeSheetResponse patchTimeSheet(TimeSheetRequest timeSheetRequest, Long timeSheetUid) {
		timeSheetRequest.setTimeSheetUid(timeSheetUid);
		
		TimeSheet timeSheet = timeSheetMapper.updateTimeSheetRequestMapper(timeSheetRequest, timeSheetRepository.findById(timeSheetUid).get());
		return timeSheetMapper.timeSheetModalMapper(timeSheetRepository.save(timeSheet));
	}

	public void deleteTimeSheet(Long timeSheetUid) {
		TimeSheet timeSheet = timeSheetMapper.deleteTimeSheetRequestMapper(1, timeSheetRepository.findById(timeSheetUid).get());
		timeSheetRepository.save(timeSheet);
	}

	private Pageable pageable(int page, int size, String sortBy, String sortDirection) {
	    Sort sort = sortDirection.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
	    return PageRequest.of(page, size, sort);
	}
	
	private LocalDateTime stringToDateConverter(String date) {
	    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
	    System.out.println(LocalDate.parse(date, inputFormatter));
	    return LocalDate.parse(date, inputFormatter).atStartOfDay();
	}
}
