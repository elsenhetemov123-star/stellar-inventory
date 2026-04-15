package com.example.product_boot.controller;

import com.example.product_boot.entity.Products;
import com.example.product_boot.repository.ProductRepository;
import com.example.product_boot.request.ProductCreateRequest;
import com.example.product_boot.response.ProductResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@CrossOrigin
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductController {

    ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductCreateRequest productCreateRequest) {
        Products products = new Products();
        products.setName(productCreateRequest.getName());
        products.setPrice(productCreateRequest.getPrice());

        Products saved = productRepository.save(products);
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(saved.getId());
        productResponse.setName(saved.getName());
        productResponse.setPrice(saved.getPrice());

        return ResponseEntity.ok(productResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        Optional<Products> optionalProducts = productRepository.findById(id);

        if (optionalProducts.isPresent()) {
            Products products = optionalProducts.get();
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(products.getId());
            productResponse.setName(products.getName());
            productResponse.setPrice(products.getPrice());

            return ResponseEntity.ok(productResponse);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {

        List<Products> all = productRepository.findAll();

        List<ProductResponse> list = all.stream()
                .map(product -> {
                    ProductResponse productResponse = new ProductResponse();
                    productResponse.setId(product.getId());
                    productResponse.setName(product.getName());
                    productResponse.setPrice(product.getPrice());
                    return productResponse;
                }).toList();
        return ResponseEntity.ok(list);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductId(@PathVariable Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductCreateRequest productCreateRequest) {
        Optional<Products> optionalProducts = productRepository.findById(id);

        if (optionalProducts.isPresent()) {
            Products products = optionalProducts.get();
            products.setName(productCreateRequest.getName());
            products.setPrice(productCreateRequest.getPrice());

            Products saved = productRepository.save(products);
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(saved.getId());
            productResponse.setName(saved.getName());
            productResponse.setPrice(saved.getPrice());

            return ResponseEntity.ok(productResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
