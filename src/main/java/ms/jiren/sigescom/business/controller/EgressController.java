package ms.jiren.sigescom.business.controller;

import lombok.RequiredArgsConstructor;
import ms.jiren.sigescom.business.controller.dto.EgressRequestDto;
import ms.jiren.sigescom.business.controller.dto.EgressResponseDto;
import ms.jiren.sigescom.business.controller.dto.ResponseDto;
import ms.jiren.sigescom.business.repository.entity.Egress;
import ms.jiren.sigescom.business.service.EgressService;
import ms.jiren.sigescom.business.util.ConvertUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/egress")
@RequiredArgsConstructor
public class EgressController {

    private final EgressService egressService;

    @GetMapping
    public ResponseEntity<List<EgressResponseDto>> getAll() {
        List<Egress> egresses = egressService.getAll();
        return ResponseEntity.ok(egresses.stream()
                .map(ConvertUtil::convertEgressEntityToEgressResponseDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EgressResponseDto> getById(@PathVariable int id) {
        try {
            Egress egress = egressService.getById(id);
            return ResponseEntity.ok(ConvertUtil.convertEgressEntityToEgressResponseDto(egress));
        } catch (Exception e) {
            System.err.println("Error al obtener el egress: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // Puedes definir una respuesta más específica aquí si lo deseas
        }
    }

    @PostMapping
    public ResponseEntity<ResponseDto> create(@RequestBody EgressRequestDto egressRequestDto) {
        try {
            egressService.save(ConvertUtil.convertEgressRequestDtoToEgressEntity(egressRequestDto));
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDto(ResponseDto.Code.COD_001, "Created successfully"));
        } catch (Exception e) {
            System.err.println("Error al crear el egress: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(ResponseDto.Code.COD_002, "Creation failed: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable int id, @RequestBody EgressRequestDto egressRequestDto) {
        try {
            egressService.update(id, ConvertUtil.convertEgressRequestDtoToEgressEntity(egressRequestDto));
            return ResponseEntity.ok(new ResponseDto(ResponseDto.Code.COD_003, "Updated successfully"));
        } catch (Exception e) {
            System.err.println("Error al actualizar el egress: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(ResponseDto.Code.COD_004, "Update failed: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable int id) {
        try {
            boolean egressEliminated = egressService.delete(id);
            if (egressEliminated) {
                return ResponseEntity.ok(new ResponseDto(ResponseDto.Code.COD_005, "Egress deleted successfully"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseDto(ResponseDto.Code.COD_006, "Egress not found"));
            }
        } catch (Exception e) {
            System.err.println("Error al eliminar el egress: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(ResponseDto.Code.COD_007, "Deletion failed: " + e.getMessage()));
        }
    }
}
