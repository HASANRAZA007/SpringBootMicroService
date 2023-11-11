package com.sbms.springbootmicroservice.service;

import com.sbms.springbootmicroservice.dto.ProductDto;
import com.sbms.springbootmicroservice.exception.CustomServiceException;
import com.sbms.springbootmicroservice.model.Product;
import com.sbms.springbootmicroservice.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ModelMapper modelMapper, ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }
    public ProductDto addProduct(ProductDto productDto){
        Product product=productRepository.findByProductCode(productDto.getProductCode());
        if(product!=null){
            productRepository.save(product);
            return this.modelMapper.map(product,ProductDto.class);
        }else {
            throw new CustomServiceException(409,"Product is already exist with same Product Code");
        }
    }
    public List<ProductDto> getAllProduct() {
        List<Product> productList = productRepository.findAll();
        List<ProductDto> productDtoList;
        productDtoList = productList.stream().map(product -> modelMapper
                        .map(productList, ProductDto.class))
                .toList();
        if (productList.isEmpty()) {
            throw new CustomServiceException(404, "Not Products data found;");
        } else {
            return productDtoList;
        }
    }
    public ProductDto getProduct(String productCode){
        Product product=productRepository.findByProductCode(productCode);
        if (product!=null){
            return this.modelMapper.map(product,ProductDto.class);
        }else {
            throw new CustomServiceException(404,"Product not Found");
        }
    }
    public ProductDto deleteProduct(String productCode){
        Product product=productRepository.findByProductCode(productCode);
        if (product!=null){
            productRepository.delete(product);
            return this.modelMapper.map(product,ProductDto.class);
        }else {
            throw new CustomServiceException(404,"Product not Found");
        }
    }
    public ProductDto updateProduct(ProductDto productDto){
        Product product=productRepository.findByProductCode(productDto.getProductCode());
        if (product!=null){
            product.setId(product.getId());
            product.setProductCode(productDto.getProductCode());
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            productRepository.save(product);
            return this.modelMapper.map(product,ProductDto.class);
        }else {
            throw new CustomServiceException(404,"Product not Found");
        }
    }

}
