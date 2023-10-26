package com.assessmenlibrary.assessmenlibrary.Record;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping (path = "api/v1/record")
public class RecordController {


    private  final RecordService recordService;


    @GetMapping("test")
    public  String test(){
        System.out.print("Hello, World!");
        return ("Hi");
    }
    @GetMapping("getrecords")
    public List<Record> getRecords(){
        return recordService.getRecords();
    }

    @PostMapping("addrecord")
    public ResponseEntity<String> registerNewRecord(@RequestBody Record record) {
        ResponseEntity<String> responseEntity = recordService.addNewRecord(record);
        return responseEntity;
    }
    @DeleteMapping(path = "delete/{recordId}")
    public ResponseEntity<String> deleteRecord(@PathVariable("recordId") Integer recordId) {
        ResponseEntity<String> responseEntity = recordService.deleteRecord(recordId);
        return responseEntity;
    }


    @PutMapping(path = "edit/{recordId}")
    public ResponseEntity<String> updateStudent(
            @PathVariable("recordId") Integer recordId,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String bookname,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer bookid,
            @RequestParam(required = false) LocalDate recorddate
    )
    {
        ResponseEntity<String> responseEntity = recordService.updateRecord(recordId, username, bookname, bookid, status,
                recorddate);
        return responseEntity;

    }

    @GetMapping(path = "getRecord/{recordId}")
    public ResponseEntity<?> getRecord(@PathVariable("recordId") Integer recordId) {
        return recordService.getRecord(recordId);
    }
    @GetMapping(path = "searchRecord/{searchWord}")
    public ResponseEntity<?> searchRecord(@PathVariable("searchWord") String searchWord) {
        return recordService.searchRecord(searchWord);
    }

}
