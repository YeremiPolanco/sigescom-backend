package ms.jiren.sigescom.business.controller.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

    private Code code;
    private String message;

    public enum Code{
        COD_001, //Created successfully
        COD_002, //Incorrectly created
        COD_003, //Updated successfully
        COD_004, //Incorrectly updated
        COD_005, //Egress deleted successfully
        COD_006, //Egress not found
        COD_007, //Deletion failed
    }
}
