package net.javaguides.pagination_shorting.contoller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.javaguides.pagination_shorting.model.Product;
import net.javaguides.pagination_shorting.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@Tag(name = "Product Management", description = "APIs for managing products with pagination and sorting")
public class ProductContoller {

    private final ProductService productService;

    @GetMapping
    @Operation(summary = "Get all products with pagination and sorting")
    public ResponseEntity<Page<Product>> getAllProducts(
           @Parameter(description = "Page number(0-based)") @RequestParam(defaultValue = "0") int page,
           @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size,
           @Parameter(description = "Sort by field") @RequestParam(defaultValue = "name") String sortBy,
           @Parameter(description = "Sort by order(asc/desc)") @RequestParam(defaultValue = "asc") String sortOrder
    ){
        return ResponseEntity.ok(productService.getAllProducts(page, size, sortBy, sortOrder));
    }

    @GetMapping("/filter")
    @Operation(summary = "Get products by category and price with pagination and sorting")
    public ResponseEntity<Page<Product>> getProductByCategoryAndPrice(
            @Parameter(description = "Category of the product") @RequestParam String category,
            @Parameter(description = "Minimum price") @RequestParam(defaultValue = "0") BigDecimal minPrice,
            @Parameter(description = "Maximum price") @RequestParam(defaultValue = "10000") BigDecimal maxPrice,
            @Parameter(description = "Page number(0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort by field") @RequestParam(defaultValue = "name") String sortBy,
            @Parameter(description = "Sort by order(asc/desc)") @RequestParam(defaultValue = "asc") String sortOrder
            ){
        return ResponseEntity.ok(productService.getProductByCategoryAndPrice(category, minPrice, maxPrice, page, size, sortBy, sortOrder));
    }
}
