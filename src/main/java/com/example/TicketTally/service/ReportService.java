package com.example.TicketTally.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TicketTally.dto.ReportDTO;
import com.example.TicketTally.entity.Report;
import com.example.TicketTally.exception.ResourceNotFoundException;
import com.example.TicketTally.repository.ReportRepository;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public List<ReportDTO> generateAnalyticsReport() {
        return reportRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<ReportDTO> getReportByEventId(Long eventId) {
        return reportRepository.findByEvent_Id(eventId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Report createReport(Report report) {
        return reportRepository.save(report);
    }

    public Report updateReport(Long id, Report updatedReport) {

        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found"));

        report.setGeneratedAt(updatedReport.getGeneratedAt());
        report.setReportType(updatedReport.getReportType());
        report.setContent(updatedReport.getContent());
        report.setName(updatedReport.getName());
        report.setType(updatedReport.getType());
        report.setEvent(updatedReport.getEvent());

        return reportRepository.save(report);
    }

    public void deleteReport(Long id) {

        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found"));

        reportRepository.delete(report);
    }

    private ReportDTO mapToDTO(Report report) {
        return new ReportDTO(
                report.getId(),
                report.getName(),
                report.getType()
        );
    }
}