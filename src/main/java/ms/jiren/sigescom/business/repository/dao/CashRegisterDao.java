package ms.jiren.sigescom.business.repository.dao;

import ms.jiren.sigescom.business.repository.entity.CashRegister;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashRegisterDao extends JpaRepository<CashRegister, Integer> {

    boolean existsByIsOpenTrue();
    CashRegister findByIsOpenTrue();
}
