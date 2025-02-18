package com.maneger.school.repository;

import com.maneger.school.domain.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogEntryRepository extends JpaRepository<LogEntry,Long> {
}
