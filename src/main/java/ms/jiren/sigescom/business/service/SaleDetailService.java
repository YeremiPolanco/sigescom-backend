package ms.jiren.sigescom.business.service;

import lombok.RequiredArgsConstructor;
import ms.jiren.sigescom.business.repository.dao.SaleDetailDao;
import ms.jiren.sigescom.business.repository.entity.SaleDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleDetailService {
    private final SaleDetailDao saleDetailDao;

    public List<SaleDetail> getAll() {
        return saleDetailDao.findAll();
    }

    public SaleDetail getById(int id) {
        return saleDetailDao.findById(id).orElse(null);
    }

    public SaleDetail save(SaleDetail saleDetail) {
        return saleDetailDao.save(saleDetail);
    }

    public SaleDetail update(int id, SaleDetail saleDetail) {
        SaleDetail saleDetail1Updated = getById(id);
        SaleDetail.builder()
                .sale(saleDetail.getSale())
                .product(saleDetail.getProduct())
                .quantity(saleDetail.getQuantity())
                .price(saleDetail.getPrice())
                .subTotal(saleDetail.getSubTotal())
                .build();

        return saleDetailDao.save(saleDetail1Updated);
    }

    public boolean delete(int id) {
        if (saleDetailDao.existsById(id)) {
            saleDetailDao.deleteById(id);
            return true;
        }
        return false;
    }
}
