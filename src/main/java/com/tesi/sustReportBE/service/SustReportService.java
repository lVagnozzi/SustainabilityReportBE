package com.tesi.sustReportBE.service;

import com.tesi.sustReportBE.model.ReportEntity;
import com.tesi.sustReportBE.repository.ReportRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

        ReportEntity entity = ReportEntity.builder()
                .fileName(fileName)
                .year(year)
                .fileData(file.getBytes())
                .build();

        reportRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public ReportEntity getReport(int year){
        Optional<ReportEntity> fileOpt = reportRepository.findByYear(year);
        if (fileOpt.isPresent()){
            ReportEntity result = fileOpt.get();
            return result;
        }
        return null;
    }

    @Transactional(readOnly = true)
    public List<Integer> getAllYears(){
        return reportRepository.findAll()
                .stream()
                .map(ReportEntity::getYear) // Prende solo l'anno
                .distinct()                 // Rimuove duplicati lato server
                .sorted(Comparator.reverseOrder()) // Riordina in modo decrescente
                .collect(Collectors.toList());
    }
}
