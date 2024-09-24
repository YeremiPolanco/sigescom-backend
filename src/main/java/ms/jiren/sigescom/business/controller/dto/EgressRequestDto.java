package ms.jiren.sigescom.business.controller.dto;

import lombok.*;
import ms.jiren.sigescom.business.repository.entity.Egress;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EgressRequestDto {
    private double amount;
    private String description;
    private Egress.Category category;
}
