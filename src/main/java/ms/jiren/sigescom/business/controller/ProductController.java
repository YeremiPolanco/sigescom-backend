package ms.jiren.sigescom.business.controller;

import lombok.RequiredArgsConstructor;
import ms.jiren.sigescom.business.controller.dto.ProductRequestDto;
import ms.jiren.sigescom.business.controller.dto.ProductResponseDto;
import ms.jiren.sigescom.business.controller.dto.ResponseDto;
import ms.jiren.sigescom.business.repository.entity.Product;
import ms.jiren.sigescom.business.service.ProductService;
import ms.jiren.sigescom.business.util.ConvertUtil;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductResponseDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "18") int size) {

        Page<Product> productPage = productService.getAll(page, size);

        Page<ProductResponseDto> productResponseDtoPage = productPage.map(
                ConvertUtil::convertProductEntityToProductResponseDto);

        return ResponseEntity.ok(productResponseDtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable int id) {
        try {
            Product product = productService.getById(id);
            return ResponseEntity.ok(ConvertUtil.convertProductEntityToProductResponseDto(product));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseDto> create(@RequestBody ProductRequestDto productRequestDto){
        try {
            productService.save(ConvertUtil.convertProductRequestDtoToProductEntity(productRequestDto));

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDto(ResponseDto.Code.COD_001, "Created successfully"));
        } catch (Exception e) {
            System.err.println("Error al crear el producto: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(ResponseDto.Code.COD_002, "Creation failed: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable int id, @RequestBody ProductRequestDto productRequestDto) {
        try {
            productService.update(id, ConvertUtil.convertProductRequestDtoToProductEntity(productRequestDto));
            return ResponseEntity.ok(new ResponseDto(ResponseDto.Code.COD_003, "Updated successfully"));
        } catch (Exception e) {
            System.err.println("Error al actualizar el product: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(ResponseDto.Code.COD_004, "Update failed: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable int id) {
        try {
            boolean productEliminated = productService.delete(id);
            if (productEliminated) {
                return ResponseEntity.ok(new ResponseDto(ResponseDto.Code.COD_005, "Product deleted successfully"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseDto(ResponseDto.Code.COD_006, "Product not found"));
            }
        } catch (Exception e) {
            System.err.println("Error al eliminar el egress: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(ResponseDto.Code.COD_007, "Deletion failed: " + e.getMessage()));
        }
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<ProductResponseDto>> getProductsWithLowStock() {
        List<Product> products = productService.getProductsWithLowStock();
        return ResponseEntity.ok(products.stream()
                .map(ConvertUtil::convertProductEntityToProductResponseDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/enable")
    public ResponseEntity<List<ProductResponseDto>> getEnabledProducts() {
        List<Product> products = productService.getEnabledProducts();
        return ResponseEntity.ok(products.stream()
                .map(ConvertUtil::convertProductEntityToProductResponseDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/disenable")
    public ResponseEntity<List<ProductResponseDto>> getDisabledProducts() {
        List<Product> products = productService.getDisabledProducts();
        return ResponseEntity.ok(products.stream()
                .map(ConvertUtil::convertProductEntityToProductResponseDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProductsByName(@RequestParam String name) {
        List<Product> products = productService.getProductsByNameContaining(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @PostMapping("/convertToBase64")
    public ResponseEntity<String> convertImageToBase64(@RequestParam("file") MultipartFile file) {
        try {
            // Convierte la imagen a bytes
            byte[] imageBytes = file.getBytes();

            // Convierte los bytes a Base64
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            // Devuelve el string en Base64
            return ResponseEntity.ok(base64Image);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la imagen.");
        }
    }

    @GetMapping("/image/{id}")
    public String getImageById(@PathVariable int id) {
        return productService.getImageById(id);
    }
}
