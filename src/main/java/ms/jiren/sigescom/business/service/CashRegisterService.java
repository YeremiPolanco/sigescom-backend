package ms.jiren.sigescom.business.service;

import lombok.RequiredArgsConstructor;
import ms.jiren.sigescom.business.repository.dao.CashRegisterDao;
import ms.jiren.sigescom.business.repository.entity.CashRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CashRegisterService {

    private final CashRegisterDao cashRegisterDao;

    public CashRegister openCashRegister() {
        if (cashRegisterDao.existsByIsOpenTrue()) {
            return null;
        }

        CashRegister cashRegister = CashRegister.builder()
                .openingTime(LocalDateTime.now())
                .isOpen(true)
                .build();
        return cashRegisterDao.save(cashRegister);
    }

    public boolean closeCashRegister() {
        CashRegister openCashRegister = cashRegisterDao.findByIsOpenTrue();
        if (openCashRegister != null) {
            openCashRegister.setClosingTime(LocalDateTime.now());
            openCashRegister.setOpen(false);
            cashRegisterDao.save(openCashRegister);
            return true;
        } else {
            return false;
        }
    }
}
