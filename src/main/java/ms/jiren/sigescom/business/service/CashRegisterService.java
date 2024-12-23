package ms.jiren.sigescom.business.service;

import lombok.RequiredArgsConstructor;
import ms.jiren.sigescom.business.repository.dao.CashRegisterDao;
import ms.jiren.sigescom.business.repository.entity.CashRegister;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CashRegisterService {

    private final CashRegisterDao cashRegisterDao;

    public CashRegister openCashRegister() {
        validateCashRegisterIsClosed();

        CashRegister cashRegister = createCashRegister();
        return cashRegisterDao.save(cashRegister);
    }

    public boolean closeCashRegister() {
        CashRegister openCashRegister = cashRegisterDao.findByIsOpenTrue()
                .orElseThrow(() -> new IllegalStateException("No hay caja abierta para cerrar."));

        closeOpenCashRegister(openCashRegister);
        return true;
    }

    private void validateCashRegisterIsClosed() {
        if (cashRegisterDao.existsByIsOpenTrue()) {
            throw new IllegalStateException("Ya hay una caja abierta.");
        }
    }

    private CashRegister createCashRegister() {
        return CashRegister.builder()
                .openingTime(LocalDateTime.now())
                .isOpen(true)
                .build();
    }

    private void closeOpenCashRegister(CashRegister cashRegister) {
        cashRegister.setClosingTime(LocalDateTime.now());
        cashRegister.setOpen(false);
        cashRegisterDao.save(cashRegister);
    }
}
