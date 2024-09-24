package ms.jiren.sigescom.business.service;

import lombok.RequiredArgsConstructor;
import ms.jiren.sigescom.business.repository.dao.SaleDao;
import ms.jiren.sigescom.business.repository.entity.Sale;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleDao saleDao;

    public List<Sale> getAll() {
        return saleDao.findAll();
    }

    public Sale getById(int id) {
        return saleDao.findById(id).orElse(null);
    }

    public Sale save(Sale sale) {
        return saleDao.save(sale);
    }

    public Sale update(int id, Sale sale) {
        Sale saleUpdated = getById(id);
        Sale.builder()
                .date(sale.getDate())
                .total(sale.getTotal())
                .build();

        return saleDao.save(sale);
    }

    public boolean delete(int id) {
        if (saleDao.existsById(id)) {
            saleDao.deleteById(id);
            return true;
        }
        return false;
    }
}
