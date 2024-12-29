package com.task.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.modal.StandardTask;

@Repository
public interface StandardTaskRepositories extends JpaRepository<StandardTask, Long> {

	Optional<StandardTask> findByUserId(Long userId);
}
