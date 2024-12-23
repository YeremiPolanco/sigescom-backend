package ms.jiren.sigescom.business.service;

import lombok.RequiredArgsConstructor;
import ms.jiren.sigescom.business.repository.dao.ProductDao;
import ms.jiren.sigescom.business.repository.dao.SaleDao;
import ms.jiren.sigescom.business.repository.dao.SaleDetailDao;
import ms.jiren.sigescom.business.repository.entity.Product;
import ms.jiren.sigescom.business.repository.entity.Sale;
import ms.jiren.sigescom.business.repository.entity.SaleDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleDetailService {
    private final SaleDetailDao saleDetailDao;
    private final ProductDao productDao;
    private final SaleDao saleDao;

    public List<SaleDetail> getAll() {
        return saleDetailDao.findAll();
    }

    public SaleDetail getById(int id) {
        return saleDetailDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Detalle de venta no encontrado con id: " + id));
    }

    @Transactional
    public SaleDetail createSaleDetail(SaleDetail saleDetail) {
        validateSaleDetail(saleDetail);

        Product product = getProductOrThrow(saleDetail.getProduct().getId());
        updateSaleDetailWithProductInfo(saleDetail, product);

        SaleDetail createdSaleDetail = saleDetailDao.save(saleDetail);
        updateSaleTotal(saleDetail.getSale().getId());

        return createdSaleDetail;
    }

    @Transactional
    public SaleDetail update(int id, SaleDetail saleDetail) {
        SaleDetail existingDetail = getById(id);
        validateQuantity(saleDetail.getQuantity());

        existingDetail.setQuantity(saleDetail.getQuantity());
        existingDetail.setPrice(saleDetail.getPrice());
        existingDetail.setSubTotal(existingDetail.getQuantity() * existingDetail.getPrice());

        return saleDetailDao.save(existingDetail);
    }

    public boolean delete(int id) {
        if (!saleDetailDao.existsById(id)) {
            throw new IllegalArgumentException("Detalle de venta no encontrado con id: " + id);
        }
        saleDetailDao.deleteById(id);
        return true;
    }

    private void updateSaleTotal(int saleId) {
        Sale sale = saleDao.findById(saleId)
                .orElseThrow(() -> new IllegalArgumentException("Venta no encontrada con id: " + saleId));

        List<SaleDetail> details = saleDetailDao.findBySaleId(saleId);
        details.forEach(detail -> detail.setSale(sale));

        double totalDetails = details.stream()
                .mapToDouble(SaleDetail::getSubTotal)
                .sum();

        sale.setTotal(totalDetails);
        saleDao.save(sale);
    }

    private void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }
    }

    private Product getProductOrThrow(int productId) {
        return productDao.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con id: " + productId));
    }

    private void validateSaleDetail(SaleDetail saleDetail) {
        validateQuantity(saleDetail.getQuantity());
        Product product = getProductOrThrow(saleDetail.getProduct().getId());
        updateSaleDetailWithProductInfo(saleDetail, product);
    }

    private void updateSaleDetailWithProductInfo(SaleDetail saleDetail, Product product) {
        saleDetail.setPrice(product.getPrice());
        saleDetail.setSubTotal(saleDetail.getQuantity() * product.getPrice());
    }
}
