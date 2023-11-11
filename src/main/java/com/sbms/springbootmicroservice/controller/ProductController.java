package com.sbms.springbootmicroservice.controller;

import com.sbms.springbootmicroservice.dto.ProductDto;
import com.sbms.springbootmicroservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping("/add")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto){
        return ResponseEntity.ok(productService.addProduct(productDto));
    }
    @PutMapping("/update")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto){
        return ResponseEntity.ok(productService.updateProduct(productDto));
    }
    @DeleteMapping("/{productCode}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable String productCode){
        return ResponseEntity.ok(productService.deleteProduct(productCode));
    }
    @GetMapping("/{productCode}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable String productCode){
        return ResponseEntity.ok(productService.getProduct(productCode));
    }
    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getAllProduct(){
        return ResponseEntity.ok(productService.getAllProduct());
    }
}
