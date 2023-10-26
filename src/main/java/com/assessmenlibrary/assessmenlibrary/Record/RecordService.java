package com.assessmenlibrary.assessmenlibrary.Record;

import com.assessmenlibrary.assessmenlibrary.Record.Record;
import com.assessmenlibrary.assessmenlibrary.Record.RecordRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RecordService {

    private final RecordRepository recordRepository;


    public RecordService(RecordRepository recordRepository){
        this.recordRepository=recordRepository;
    }
    //Get all Record
    public List<Record> getRecords(){
        return recordRepository.findAll();

    }
    //get Record by id
    public ResponseEntity<?> getRecord(Integer recordId) {
        Optional<Record> optionalRecord = recordRepository.findById(recordId);

        if (optionalRecord.isPresent()) {
            return ResponseEntity.ok(optionalRecord.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No record found with ID: " + recordId);
        }
    }

    //search book by name
    public ResponseEntity<?> searchRecord(String searchWord) {
        Optional<Record> optionalRecord = recordRepository.findRecordByUsername(searchWord);

        if (optionalRecord.isPresent()) {
            return ResponseEntity.ok(optionalRecord.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No record found with Name " + searchWord);
        }
    }

    //add new record
    public ResponseEntity<String> addNewRecord(Record record) {
        recordRepository.save(record);

        return ResponseEntity.status(HttpStatus.CREATED).body("Record registered successfully");
    }

    //delete Record by id
    public ResponseEntity<String> deleteRecord(Integer recordId){
        boolean exists= recordRepository.existsById(recordId);
        if(!exists){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("record does not exist");
        }
        recordRepository.deleteById(recordId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Record deleted successfully");

    }

    //edit Record
    @Transactional
    public ResponseEntity<String> updateRecord(Integer recordId, String username, String bookname, Integer bookid, String status,
                                              LocalDate recorddate) {

        Optional<Record> optionalRecord = recordRepository.findById(recordId);

        if (optionalRecord.isPresent()) {
            Record record = optionalRecord.get();

            if (username != null && !username.isEmpty() && !Objects.equals(record.getUsername(), username)) {
                Optional<Record> recordByName = recordRepository.findRecordByUsername(username);

                if (recordByName.isPresent()) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Record already Registered: " + bookname);
                }

                record.setUsername(username);
                return ResponseEntity.status(HttpStatus.OK).body("Record updated successfully");
            }

            if (bookname != null && !bookname.isEmpty() && !Objects.equals(record.getBookname(), bookname)) {
                record.setBookname(bookname);
            }

            if (status != null && !status.isEmpty() && !Objects.equals(record.getStatus(), status)) {
                record.setStatus(status);
            }

            if (recorddate != null) {
                record.setRecorddate(recorddate);
            }
            recordRepository.save(record); // Save changes to the database

            return ResponseEntity.status(HttpStatus.OK).body("Record updated successfully");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found with ID: " + recordId);
    }
}
