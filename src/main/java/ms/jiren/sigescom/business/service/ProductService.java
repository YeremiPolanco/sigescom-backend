package ms.jiren.sigescom.business.service;

import lombok.RequiredArgsConstructor;
import ms.jiren.sigescom.business.repository.dao.ProductDao;
import ms.jiren.sigescom.business.repository.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao productDao;

    public Page<Product> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productDao.findAll(pageable);
    }

    public Product getById(int id) {
        return productDao.findById(id).orElse(null);
    }

    public Product save(Product product) {
        if (product.getStock() >= 0){
            System.out.println("product: " + product.getImage());
            return productDao.save(product);
        }
        return null;
    }


    public Product update(int id, Product product) {
        Product productUpdated = getById(id);
        Product.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .cost(product.getCost())
                .stock(product.getStock())
                .umbralStock(product.getUmbralStock())
                .category(product.getCategory())
                .enable(product.isEnable())
                .createDate(product.getCreateDate())
                .build();

        return productDao.save(productUpdated);
    }

    public boolean delete(int id) {
        if (productDao.existsById(id)) {
            productDao.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Product> getProductsWithLowStock() {
        return productDao.findProductsWithLowStock();
    }

    public List<Product> getEnabledProducts(){
        return productDao.findEnabledProducts();
    }

    public List<Product> getDisabledProducts(){
        return productDao.findDisabledProducts();
    }

    public List<Product> getProductsByNameContaining(String name){
        return productDao.findProductsByNameContaining(name);
    }

    public String getImageById(int id) {
        return productDao.findImageById(id);
    }
}
