package com.example.TicketTally.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.TicketTally.service.GateControlService;

@RestController
@RequestMapping("/api/gate")
public class GateController {

    @Autowired
    private GateControlService gateControlService;

    @PostMapping("/scan")
    @PreAuthorize("hasRole('GATE_CONTROLLER')")
    public ResponseEntity<?> scanTicket(@RequestParam String serialNumber,
                                        @RequestParam Long controllerId) {

        return ResponseEntity.ok(
                gateControlService.processEntry(serialNumber, controllerId)
        );
    }
}