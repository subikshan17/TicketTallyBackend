package com.example.TicketTally.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TicketTally.entity.GateLog;

@Repository
public interface GateLogRepository extends JpaRepository<GateLog, Long> {

    List<GateLog> findByTicket_Id(Long ticketId);

    boolean existsByTicket_IdAndStatus(Long ticketId, String status);

}