package ms.jiren.sigescom.business.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CashRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDateTime openingTime;

    @Column(nullable = true)
    private LocalDateTime closingTime;

    @Column(nullable = false)
    private boolean isOpen;

    @OneToMany(mappedBy = "cashRegister", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Sale> sales;

}
