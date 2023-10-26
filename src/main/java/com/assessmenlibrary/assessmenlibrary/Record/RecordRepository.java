package com.assessmenlibrary.assessmenlibrary.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecordRepository extends JpaRepository<Record, Integer> {

    Optional<Record> findRecordByUsername(String username);
}