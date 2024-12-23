package ms.jiren.sigescom.business.repository.dao;

import ms.jiren.sigescom.business.repository.entity.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleDetailDao extends JpaRepository<SaleDetail, Integer> {
    List<SaleDetail> findBySaleId(int saleId);
}
