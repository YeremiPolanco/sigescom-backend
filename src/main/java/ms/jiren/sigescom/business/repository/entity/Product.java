package ms.jiren.sigescom.business.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private double cost;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private int umbralStock;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private boolean enable;

    @Column(nullable = false)
    private Date createDate;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String image;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SaleDetail> details;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InventoryMovement> inventoryMovements;

    @PrePersist
    public void prePersist() {
        this.createDate = new Date();
        this.enable = true;
    }
}
