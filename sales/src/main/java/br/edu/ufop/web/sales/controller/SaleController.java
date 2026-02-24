package br.edu.ufop.web.sales.controller;

import br.edu.ufop.web.sales.business.services.SaleService;
import br.edu.ufop.web.sales.controller.dtos.events.CreateSaleDTO;
import br.edu.ufop.web.sales.controller.dtos.events.DeleteSaleDTO;
import br.edu.ufop.web.sales.controller.dtos.events.SaleDTO;
import br.edu.ufop.web.sales.controller.dtos.events.UpdateSaleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {
    private final SaleService saleService;

    @GetMapping
    public ResponseEntity<List<SaleDTO>> getAll() {
        return ResponseEntity.ok(saleService.getAll());
    }

    @PostMapping
    public ResponseEntity<SaleDTO> create(@RequestBody CreateSaleDTO createSaleDTO) {
        return ResponseEntity.ok(saleService.create(createSaleDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> getById(@PathVariable(value = "id") UUID id) {
        try {
            SaleDTO saleDTO = saleService.getById(id);
            return ResponseEntity.ok(saleDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDTO> update(
            @PathVariable(value = "id") UUID id,
            @RequestBody UpdateSaleDTO updateSaleDTO) {
        try {
            SaleDTO saleDTO = saleService.update(id, updateSaleDTO);
            return ResponseEntity.ok(saleDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody DeleteSaleDTO deleteSaleDTO) {
        try {
            saleService.delete(deleteSaleDTO);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
