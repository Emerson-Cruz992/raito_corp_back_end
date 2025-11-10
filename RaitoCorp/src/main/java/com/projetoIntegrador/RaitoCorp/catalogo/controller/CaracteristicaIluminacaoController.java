package com.projetoIntegrador.RaitoCorp.catalogo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetoIntegrador.RaitoCorp.catalogo.model.CaracteristicaIluminacao;
import com.projetoIntegrador.RaitoCorp.catalogo.service.CaracteristicaIluminacaoService;

@RestController
@RequestMapping("/api/iluminacao")
@CrossOrigin(origins = "*")
public class CaracteristicaIluminacaoController {

    private final CaracteristicaIluminacaoService service;

    public CaracteristicaIluminacaoController(CaracteristicaIluminacaoService service) {
        this.service = service;
    }

    @GetMapping("/produto/{idProduto}")
    public ResponseEntity<List<CaracteristicaIluminacao>> listarPorProduto(@PathVariable UUID idProduto) {
        return ResponseEntity.ok(service.listarPorProduto(idProduto));
    }

    @PostMapping("/produto/{idProduto}")
    public ResponseEntity<CaracteristicaIluminacao> adicionar(
            @PathVariable UUID idProduto,
            @RequestParam String potencia,
            @RequestParam String temperaturaCor,
            @RequestParam String fluxoLuminoso,
            @RequestParam(required = false) String tensao,
            @RequestParam(required = false) String eficiencia,
            @RequestParam(required = false) String indiceProtecao
    ) {
        return ResponseEntity.ok(service.adicionar(idProduto, potencia, temperaturaCor, fluxoLuminoso, tensao, eficiencia, indiceProtecao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CaracteristicaIluminacao> atualizar(
            @PathVariable UUID id,
            @RequestBody CaracteristicaIluminacao dados
    ) {
        return ResponseEntity.ok(service.atualizar(id, dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable UUID id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
