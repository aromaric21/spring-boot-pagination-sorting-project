package net.javaguides.pagination_shorting.repository;

import net.javaguides.pagination_shorting.model.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByCategory(String category, Pageable pageable);

    @Query("""
            SELECT p FROM Product p
            WHERE p.category = :category
            AND (p.price BETWEEN :minPrice AND :maxPrice)
            """)
    Page<Product> findByCategoryAndPrice(
            @Param("category") String category,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            Pageable pageable
    );
}
