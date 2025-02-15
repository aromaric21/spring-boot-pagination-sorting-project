package net.javaguides.pagination_shorting.service;

import lombok.RequiredArgsConstructor;
import net.javaguides.pagination_shorting.model.Product;
import net.javaguides.pagination_shorting.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<Product> getAllProducts(
        int page,
        int size,
        String sortBy,
        String sortOrder
    ){
        var sort = sortOrder.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        var pageable = PageRequest.of(page, size, sort);
        return productRepository.findAll(pageable);
    }

    public Page<Product> getProductByCategoryAndPrice(
            String category,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            int page,
            int size,
            String sortBy,
            String sortOrder
    ){
        var sort = sortOrder.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        var pageable = PageRequest.of(page, size, sort);
        return productRepository.findByCategoryAndPrice(
                category,
                minPrice,
                maxPrice,
                pageable
        );
    }
}
