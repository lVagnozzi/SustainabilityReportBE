package com.tesi.sustReportBE.service;

import com.tesi.sustReportBE.model.ReportEntity;
import com.tesi.sustReportBE.repository.ReportRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
public class SustReportService {

    private static boolean error = false;

    //Dependency Injection per costruttore
    private final ReportRepository reportRepository;
    public SustReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Transactional
    public void salva(MultipartFile file, int year) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        ReportEntity entity = new ReportEntity(fileName,year,file.getBytes());
        reportRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public ReportEntity getReport(int year){
        Optional<ReportEntity> fileOpt = reportRepository.findByYear(year);
        if (fileOpt.isPresent()){
            return fileOpt.get();
        }
        throw new RuntimeException("Report not found for year = " + year);
    }
}
