package com.task.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.task.modal.TimeSheet;

@Repository
public interface TimeSheetRepositories extends JpaRepository<TimeSheet, Long> {

//	@Query("SELECT t FROM TimeSheet t WHERE (:userId IS NULL OR t.userId = :userId) AND (:startDateTime IS NULL OR t.startDateTime = :startDateTime) AND t.deleteFlag=0")
//	Optional<TimeSheet> findByTimeSheetUidOrStartDateTime(@Param("userId") Long userId, @Param("startDateTime") LocalDateTime startDateTime);

	@Query("SELECT t FROM TimeSheet t WHERE (:userId IS NULL OR t.userId = :userId) AND t.deleteFlag = 0")
	Optional<TimeSheet> findByTimeSheetUidOrStartAndEndDateTime(@Param("userId") Long userId);

	@Query("SELECT t FROM TimeSheet t WHERE (:userId IS NULL OR t.userId = :userId) AND (:startOfDay IS NULL OR t.startDateTime >= :startOfDay AND t.startDateTime < :endOfDay) AND t.deleteFlag = 0")
	Page<TimeSheet> findAllByTimeSheetUidOrStartAndEndDateTime(@Param("userId") Long userId, @Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay, Pageable page);

	
//	@Query("SELECT t FROM TimeSheet t WHERE (:userId IS NULL OR t.userId = :userId) " +
//		       "AND (:startDateTime IS NULL OR (t.startDateTime >= :startOfDay AND t.startDateTime < :endOfDay)) " +
//		       "AND t.deleteFlag = 0")
//		Optional<TimeSheet> findByTimeSheetUidOrStartDateTime(
//		    @Param("userId") Long userId, 
//		    @Param("startOfDay") LocalDateTime startOfDay, 
//		    @Param("endOfDay") LocalDateTime endOfDay);

}
