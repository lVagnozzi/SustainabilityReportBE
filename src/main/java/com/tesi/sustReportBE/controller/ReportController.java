package com.tesi.sustReportBE.controller;

import com.tesi.sustReportBE.model.ReportEntity;
import com.tesi.sustReportBE.repository.ReportRepository;
import com.tesi.sustReportBE.service.SustReportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "http://localhost:4200")
public class ReportController {

    //Dependency Injection per costruttore
    private final SustReportService sustReportService;
    public ReportController(SustReportService sustReportService) {
        this.sustReportService = sustReportService;
    }

    @GetMapping("/get/{year}")
    public ResponseEntity<?> getSustReport(@PathVariable("year") int year) {
        // Recupera l'entità dal service
        ReportEntity report = sustReportService.getReport(year);

        // Controlla se il report esiste (best practice)
        if (report == null || report.getFileData() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Report non trovato per l'anno " + year);
        }

        // Restituisco direttamente i byte con l'header corretto
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Eni_Sustainability_Report-" + year + ".pdf\"")
                .body(report.getFileData());
    }

    @PostMapping("/upload")
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
