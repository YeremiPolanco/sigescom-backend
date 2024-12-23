package ms.jiren.sigescom.business.service;

import lombok.RequiredArgsConstructor;
import ms.jiren.sigescom.business.repository.dao.CashRegisterDao;
import ms.jiren.sigescom.business.repository.dao.SaleDao;
import ms.jiren.sigescom.business.repository.dao.SaleDetailDao;
import ms.jiren.sigescom.business.repository.entity.CashRegister;
import ms.jiren.sigescom.business.repository.entity.Sale;
import ms.jiren.sigescom.business.repository.entity.SaleDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleDao saleDao;
    private final CashRegisterDao cashRegisterDao;

    // Obtener todas las ventas
    public List<Sale> getAllSales() {
        return saleDao.findAll();
    }

    // Obtener una venta por ID
    public Sale getSaleById(int id) {
        return saleDao.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Venta no encontrada con id: " + id));
    }

    // Crear una nueva venta
    public Sale createSale(Sale sale) {
        CashRegister openCashRegister = getOpenCashRegister();
        initializeSale(sale, openCashRegister);
        return saleDao.save(sale);
    }

    // Actualizar una venta existente
    public Sale updateSale(int id, Sale sale) {
        Sale existingSale = getSaleById(id);
        updateSaleDetails(existingSale, sale);
        return saleDao.save(existingSale);
    }

    // Eliminar una venta
    public boolean deleteSale(int id) {
        if (saleDao.existsById(id)) {
            saleDao.deleteById(id);
            return true;
        }
        return false;
    }

    // Obtener la caja abierta
    private CashRegister getOpenCashRegister() {
        return cashRegisterDao.findByIsOpenTrue()
                .orElseThrow(() -> new IllegalStateException("No hay ninguna caja abierta."));
    }

    // Inicializar una nueva venta
    private void initializeSale(Sale sale, CashRegister openCashRegister) {
        sale.setTotal(0);
        sale.setCashRegister(openCashRegister);
    }

    // Actualizar los detalles de una venta existente
    private void updateSaleDetails(Sale existingSale, Sale newSale) {
        existingSale.setDate(newSale.getDate());
        existingSale.setType(newSale.getType());

        double total = calculateTotal(existingSale.getDetails());
        existingSale.setTotal(total);
    }

    // Calcular el total de los detalles de venta
    private double calculateTotal(List<SaleDetail> saleDetails) {
        return saleDetails.stream()
                .mapToDouble(SaleDetail::getSubTotal)
                .sum();
    }
}
