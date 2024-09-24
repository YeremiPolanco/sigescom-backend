package ms.jiren.sigescom.business.repository.dao;

import ms.jiren.sigescom.business.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT * FROM product WHERE stock < umbral_stock", nativeQuery = true)
    List<Product> findProductsWithLowStock();

    @Query(value = "SELECT * FROM product WHERE enable = true", nativeQuery = true)
    List<Product> findEnabledProducts();

    @Query(value = "SELECT * FROM product WHERE enable = false", nativeQuery = true)
    List<Product> findDisabledProducts();

    @Query(value = "SELECT * FROM product WHERE name LIKE %:name%", nativeQuery = true)
    List<Product> findProductsByNameContaining(@Param("name") String name);

    @Query(value = "SELECT image FROM product WHERE id = :id", nativeQuery = true)
    String findImageById(@Param("id") int id);
}
