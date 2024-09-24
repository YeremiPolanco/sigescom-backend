package ms.jiren.sigescom.business.repository.dao;

import ms.jiren.sigescom.business.repository.entity.InventoryMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryMovementDao extends JpaRepository<InventoryMovement, Integer> {
}
