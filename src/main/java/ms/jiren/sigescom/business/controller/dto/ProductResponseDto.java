package ms.jiren.sigescom.business.controller.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {
    private int id;
    private String name;
    private String description;
    private double price;
    private double cost;
    private int stock;
    private int umbralStock;
    private String category;
    private boolean enable;
    private Date createDate;
    private String image;
}
