package com.dolts.controledehotel.controllers;

import com.dolts.controledehotel.models.HospedeModel;
import com.dolts.controledehotel.services.HospedeService;
import com.dolts.controledehotel.services.JasperReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class JasperReportController {

    private final JasperReportService reportService;
    private final HospedeService hospedeService;

    @Autowired
    public JasperReportController(JasperReportService reportService, HospedeService hospedeService) {
        this.reportService = reportService;
        this.hospedeService = hospedeService;
    }

    @GetMapping("/relatorio")
    public ResponseEntity<byte[]> gerarRelatorio() {
        Map<String, Object> parametros = new HashMap<>();

        List<HospedeModel> dadosDoRelatorio = hospedeService.findAll();

        byte[] relatorio = reportService.generateReport("Clientes", parametros, dadosDoRelatorio);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.attachment().filename("relatorio.pdf").build());

        return new ResponseEntity<>(relatorio, headers, HttpStatus.OK);
    }
}

