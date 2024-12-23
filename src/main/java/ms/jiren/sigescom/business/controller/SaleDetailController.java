package ms.jiren.sigescom.business.controller;

import lombok.RequiredArgsConstructor;
import ms.jiren.sigescom.business.repository.entity.SaleDetail;
import ms.jiren.sigescom.business.service.SaleDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale-details")
@RequiredArgsConstructor
public class SaleDetailController {

    private final SaleDetailService saleDetailService;

    // Obtener todos los detalles de ventas
    @GetMapping
    public ResponseEntity<List<SaleDetail>> getAllSaleDetails() {
        List<SaleDetail> saleDetails = saleDetailService.getAll();
        return new ResponseEntity<>(saleDetails, HttpStatus.OK);
    }

    // Obtener un detalle de venta por ID
    @GetMapping("/{id}")
    public ResponseEntity<SaleDetail> getSaleDetailById(@PathVariable int id) {
        try {
            SaleDetail saleDetail = saleDetailService.getById(id);
            return new ResponseEntity<>(saleDetail, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo detalle de venta
    @PostMapping
    public ResponseEntity<SaleDetail> createSaleDetail(@RequestBody SaleDetail saleDetail) {
        System.out.println("saleDetail: " + saleDetail);
        try {
            SaleDetail newSaleDetail = saleDetailService.createSaleDetail(saleDetail);
            return new ResponseEntity<>(newSaleDetail, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Actualizar un detalle de venta existente
    @PutMapping("/{id}")
    public ResponseEntity<SaleDetail> updateSaleDetail(@PathVariable int id, @RequestBody SaleDetail saleDetail) {
        try {
            SaleDetail updatedSaleDetail = saleDetailService.update(id, saleDetail);
            return new ResponseEntity<>(updatedSaleDetail, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un detalle de venta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSaleDetail(@PathVariable int id) {
        try {
            saleDetailService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
