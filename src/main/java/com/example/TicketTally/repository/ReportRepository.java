package com.example.TicketTally.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TicketTally.entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findByEvent_Id(Long eventId);

}