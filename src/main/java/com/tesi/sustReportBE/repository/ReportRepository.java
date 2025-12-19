package com.tesi.sustReportBE.repository;

import com.tesi.sustReportBE.model.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity,Long> {
    Optional<ReportEntity> findByYear(int year);

}
