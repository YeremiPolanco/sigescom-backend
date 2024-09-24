package ms.jiren.sigescom.business.repository.dao;

import ms.jiren.sigescom.business.repository.entity.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDetailDao extends JpaRepository<SaleDetail, Integer> {
}
