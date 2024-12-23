package ms.jiren.sigescom.business.controller;

import lombok.RequiredArgsConstructor;
import ms.jiren.sigescom.business.repository.entity.Sale;
import ms.jiren.sigescom.business.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    // Obtener todas las ventas
    @GetMapping
    public ResponseEntity<List<Sale>> getAllSales() {
        List<Sale> sales = saleService.getAllSales();
        return ResponseEntity.ok(sales);
    }

    // Obtener una venta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable int id) {
        try {
            Sale sale = saleService.getSaleById(id);
            return ResponseEntity.ok(sale);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Crear una nueva venta
    @PostMapping
    public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
        try {
            Sale newSale = saleService.createSale(sale);
            return ResponseEntity.status(HttpStatus.CREATED).body(newSale);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Actualizar una venta
    @PutMapping("/{id}")
    public ResponseEntity<Sale> updateSale(@PathVariable int id, @RequestBody Sale sale) {
        try {
            Sale updatedSale = saleService.updateSale(id, sale);
            return ResponseEntity.ok(updatedSale);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Eliminar una venta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable int id) {
        boolean deleted = saleService.deleteSale(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
