package ms.jiren.sigescom.business.util;

import lombok.experimental.UtilityClass;
import ms.jiren.sigescom.business.controller.dto.EgressRequestDto;
import ms.jiren.sigescom.business.controller.dto.EgressResponseDto;
import ms.jiren.sigescom.business.controller.dto.ProductRequestDto;
import ms.jiren.sigescom.business.controller.dto.ProductResponseDto;
import ms.jiren.sigescom.business.repository.entity.Egress;
import ms.jiren.sigescom.business.repository.entity.Product;

@UtilityClass
public class ConvertUtil {
    //Egress convert

    public EgressResponseDto convertEgressEntityToEgressResponseDto(Egress egress) {
        return EgressResponseDto.builder()
                .id(egress.getId())
                .description(egress.getDescription())
                .amount(egress.getAmount())
                .date(egress.getDate())
                .category(egress.getCategory())
                .build();
    }

    public Egress convertEgressRequestDtoToEgressEntity(EgressRequestDto egressRequestDto) {
        return Egress.builder()
                .description(egressRequestDto.getDescription())
                .amount(egressRequestDto.getAmount())
                .category(egressRequestDto.getCategory())
                .build();
    }

    //Product convert

    public ProductResponseDto convertProductEntityToProductResponseDto(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .cost(product.getCost())
                .stock(product.getStock())
                .umbralStock(product.getUmbralStock())
                .category(product.getCategory())
                .enable(product.isEnable())
                .createDate(product.getCreateDate())
                .image(product.getImage())
                .build();
    }

    public Product convertProductRequestDtoToProductEntity(ProductRequestDto productRequestDto) {
        return Product.builder()
                .name(productRequestDto.getName())
                .description(productRequestDto.getDescription())
                .price(productRequestDto.getPrice())
                .cost(productRequestDto.getCost())
                .stock(productRequestDto.getStock())
                .umbralStock(productRequestDto.getUmbralStock())
                .category(productRequestDto.getCategory())
                .image(productRequestDto.getImage())
                .build();

    }

}
