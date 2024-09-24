package ms.jiren.sigescom.business.service;

import lombok.RequiredArgsConstructor;
import ms.jiren.sigescom.business.repository.dao.InventoryMovementDao;
import ms.jiren.sigescom.business.repository.entity.InventoryMovement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryMovementService {
    private final InventoryMovementDao inventoryMovementDao;

    public List<InventoryMovement> getAll() {
        return inventoryMovementDao.findAll();
    }

    public InventoryMovement getById(int id) {
        return inventoryMovementDao.findById(id).orElse(null);
    }

    public InventoryMovement save(InventoryMovement inventoryMovement) {
        return inventoryMovementDao.save(inventoryMovement);
    }

    public InventoryMovement update(int id, InventoryMovement inventoryMovement) {
        InventoryMovement inventoryMovementUpdated = getById(id);

        InventoryMovement.builder()
                .product(inventoryMovement.getProduct())
                .type(inventoryMovement.getType())
                .quantity(inventoryMovement.getQuantity())
                .date(inventoryMovement.getDate())
                .description(inventoryMovement.getDescription())
                .build();

        return inventoryMovementDao.save(inventoryMovementUpdated);
    }

    public boolean delete(int id) {
        if (inventoryMovementDao.existsById(id)) {
            inventoryMovementDao.deleteById(id);
            return true;
        }
        return false;
    }
}
