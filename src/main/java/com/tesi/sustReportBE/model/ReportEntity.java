package com.tesi.sustReportBE.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "report")
public class ReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private int year;

    @Lob
    @Column(columnDefinition = "LONGBLOB", nullable = false)
    private byte[] fileData;

    public ReportEntity(){}

    public ReportEntity(String filename, int year, byte[] fileData) {
        this.filename = filename;
        this.year = year;
        this.fileData = fileData;
    }
}
