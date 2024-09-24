package ms.jiren.sigescom.business.repository.dao;

import ms.jiren.sigescom.business.repository.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDao extends JpaRepository<Sale, Integer> {
}
