package com.projetoIntegrador.RaitoCorp.catalogo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetoIntegrador.RaitoCorp.catalogo.model.CaracteristicaIluminacao;
import com.projetoIntegrador.RaitoCorp.catalogo.service.CaracteristicaIluminacaoService;

@RestController
@RequestMapping("/api/iluminacao")
public class CaracteristicaIluminacaoController {

    private final CaracteristicaIluminacaoService service;

    public CaracteristicaIluminacaoController(CaracteristicaIluminacaoService service) {
        this.service = service;
    }

    // 4.1 - Criar
    @PostMapping("/produto/{idProduto}")
    public ResponseEntity<CaracteristicaIluminacao> adicionar(
            @PathVariable UUID idProduto,
            @RequestParam String potencia,
            @RequestParam String temperaturaCor,
            @RequestParam String fluxoLuminoso,
            @RequestParam(required = false) String tensao,
            @RequestParam(required = false) String eficiencia,
            @RequestParam(required = false) String indiceProtecao,
            @RequestParam(defaultValue = "false") boolean regulavel
    ) {
        CaracteristicaIluminacao c = service.adicionar(
                idProduto,
                potencia,
                temperaturaCor,
                fluxoLuminoso,
                tensao,
                eficiencia,
                indiceProtecao,
                regulavel
        );

        return ResponseEntity.ok(c);
    }

    // 4.2 - Buscar todas por produto
    @GetMapping("/produto/{idProduto}")
    public ResponseEntity<List<CaracteristicaIluminacao>> listarPorProduto(@PathVariable UUID idProduto) {
        return ResponseEntity.ok(service.listarPorProduto(idProduto));
    }

    // 4.3 - Buscar característica por ID
    @GetMapping("/{id}")
    public ResponseEntity<CaracteristicaIluminacao> buscarPorId(@PathVariable UUID id) {
        CaracteristicaIluminacao c = service.buscarPorId(id);
        return ResponseEntity.ok(c);
    }

    // 4.4 - Atualizar
    @PutMapping("/{id}")
public ResponseEntity<CaracteristicaIluminacao> atualizar(
        @PathVariable UUID id,
        @RequestParam(required = false) String potencia,
        @RequestParam(required = false) String temperaturaCor,
        @RequestParam(required = false) String fluxoLuminoso,
        @RequestParam(required = false) String tensao,
        @RequestParam(required = false) String eficiencia,
        @RequestParam(required = false) String indiceProtecao,
        @RequestParam(required = false) Boolean regulavel
) {

    CaracteristicaIluminacao dados = new CaracteristicaIluminacao();
    dados.setPotencia(potencia);
    dados.setTemperaturaCor(temperaturaCor);
    dados.setFluxoLuminoso(fluxoLuminoso);
    dados.setTensao(tensao);
    dados.setEficiencia(eficiencia);
    dados.setIndiceProtecao(indiceProtecao);
    if (regulavel != null) dados.setRegulavel(regulavel);

    CaracteristicaIluminacao atualizado = service.atualizar(id, dados);

    return ResponseEntity.ok(atualizado);
}


    // 4.5 - Excluir
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable UUID id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
