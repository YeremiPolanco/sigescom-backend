package ms.jiren.sigescom.business.controller.dto;

import lombok.*;
import ms.jiren.sigescom.business.repository.entity.Egress;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EgressResponseDto {
    private int id;
    private Date date;
    private double amount;
    private String description;
    private Egress.Category category;
}
