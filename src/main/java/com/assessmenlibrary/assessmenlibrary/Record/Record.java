package com.assessmenlibrary.assessmenlibrary.Record;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="_record")
public class Record {
    @Id
    @GeneratedValue
    private int id;
    private String username;
    private LocalDate recorddate;
    private String bookname;
    private Integer bookid;
    private String status;

}
