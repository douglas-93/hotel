package com.dolts.controledehotel.controllers;

import com.dolts.controledehotel.models.HospedeModel;
import com.dolts.controledehotel.services.HospedeService;
import com.dolts.controledehotel.services.JasperReportService;
import com.dolts.controledehotel.utils.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping("/relatorios")
public class JasperReportController {

    private final JasperReportService reportService;
    private final HospedeService hospedeService;

    @Autowired
    public JasperReportController(JasperReportService reportService, HospedeService hospedeService) {
        this.reportService = reportService;
        this.hospedeService = hospedeService;
    }

    @GetMapping("/hospedes")
    public ResponseEntity<byte[]> gerarRelatorio() {
        Formatter f = new Formatter();
        List<HospedeModel> dadosDoRelatorio = hospedeService.findAll();
        dadosDoRelatorio.forEach(h -> {
            h.setCpf(f.formatarCPF(h.getCpf()));
            if (h.getCelular() != null)
                h.setCelular(f.formatarCelular(h.getCelular()));
            if (h.getTelefone() != null)
                h.setTelefone(f.formatarTelefone(h.getTelefone()));
        });
        return constroiRelatorio("Clientes", dadosDoRelatorio, "hospedes");
    }

    public ResponseEntity<byte[]> constroiRelatorio(String nomeRelatorio, List<?> dados, String nomeArquivo) {
        Map<String, Object> parametros = new HashMap<>();

        List<?> dadosDoRelatorio = dados;

        byte[] relatorio = reportService.generateReport(nomeRelatorio, parametros, dadosDoRelatorio);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.attachment().filename(nomeArquivo + ".pdf").build());

        return new ResponseEntity<>(relatorio, headers, HttpStatus.OK);
    }

}

