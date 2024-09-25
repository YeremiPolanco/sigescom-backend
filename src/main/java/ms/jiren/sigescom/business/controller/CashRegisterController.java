package ms.jiren.sigescom.business.controller;

import ms.jiren.sigescom.business.repository.entity.CashRegister;
import ms.jiren.sigescom.business.service.CashRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cash-register")
public class CashRegisterController {

    @Autowired
    private CashRegisterService cashRegisterService;

    @PostMapping("/open")
    public ResponseEntity<String> openCashRegister() {
        CashRegister cashRegister = cashRegisterService.openCashRegister();
        if (cashRegister != null) {
            return ResponseEntity.ok("Cash register opened successfully.");
        } else {
            return ResponseEntity.status(409).body("There is already an open cash register.");
        }
    }

    @PostMapping("/close")
    public ResponseEntity<String> closeCashRegister() {
        boolean closed = cashRegisterService.closeCashRegister();
        if (closed) {
            return ResponseEntity.ok("Cash register closed successfully.");
        } else {
            return ResponseEntity.status(400).body("No open cash register found.");
        }
    }
}
