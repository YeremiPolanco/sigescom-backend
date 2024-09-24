package ms.jiren.sigescom.business.controller.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProductRequestDto {

    private String name;
    private String description;
    private double price;
    private double cost;
    private int stock;
    private int umbralStock;
    private String category;
    private String image;
}
