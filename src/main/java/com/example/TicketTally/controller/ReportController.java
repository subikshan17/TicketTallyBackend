package com.example.TicketTally.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.TicketTally.entity.Report;
import com.example.TicketTally.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<?> getReports() {
        return ResponseEntity.ok(reportService.generateAnalyticsReport());
    }

    @GetMapping("/event/{eventId}")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<?> getReportByEventId(@PathVariable Long eventId) {
        return ResponseEntity.ok(reportService.getReportByEventId(eventId));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN','EVENT_ORGANIZER')")
    public ResponseEntity<Report> createReport(@RequestBody Report report) {
        return ResponseEntity.ok(reportService.createReport(report));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN','EVENT_ORGANIZER')")
    public ResponseEntity<Report> updateReport(@PathVariable Long id,
                                               @RequestBody Report report) {
        return ResponseEntity.ok(reportService.updateReport(id, report));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN','EVENT_ORGANIZER')")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }
}