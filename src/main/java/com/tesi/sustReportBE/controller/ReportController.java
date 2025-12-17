package com.tesi.sustReportBE.controller;

import com.tesi.sustReportBE.model.ReportEntity;
import com.tesi.sustReportBE.repository.ReportRepository;
import com.tesi.sustReportBE.service.SustReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    //Dependency Injection per costruttore
    private final SustReportService sustReportService;
    public ReportController(SustReportService sustReportService) {
        this.sustReportService = sustReportService;
    }

    @GetMapping("/get")
    public ResponseEntity<ReportEntity> getSustReport(@RequestParam("year") int year){
        return ResponseEntity.ok(sustReportService.getReport(year));
    }

    @GetMapping("/upload")
    public ResponseEntity<?> saveSustReport(@RequestParam("file") MultipartFile file, @RequestParam("year") int year)
            throws IOException {
        try {
            sustReportService.salva(file,year);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Errore upload");
        }
        return ResponseEntity.ok("File caricato con successo");
    }

}
