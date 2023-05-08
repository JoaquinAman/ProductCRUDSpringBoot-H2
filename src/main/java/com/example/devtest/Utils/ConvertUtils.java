package com.example.devtest.Utils;

import com.example.devtest.DTO.Request.ProductRequestDTO;
import com.example.devtest.DTO.Response.ProductResponseDTO;
import com.example.devtest.Model.Product;
import org.springframework.stereotype.Component;

@Component("convertUtils")
public class ConvertUtils {
    public ProductResponseDTO convertToProductResponseDTO(Product product) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(product.getId());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setDescription(product.getDescription());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setQuantity(product.getQuantity());
        return productResponseDTO;
    }
    public Product convertToProduct(ProductResponseDTO productResponseDTO) {
        Product product = new Product();
        product.setId(productResponseDTO.getId());
        product.setName(productResponseDTO.getName());
        product.setDescription(productResponseDTO.getDescription());
        product.setPrice(productResponseDTO.getPrice());
        product.setQuantity(productResponseDTO.getQuantity());
        return product;
    }

    public Product convertToProduct(ProductRequestDTO productRequestDTO) {
        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setDescription(productRequestDTO.getDescription());
        product.setPrice(productRequestDTO.getPrice());
        product.setQuantity(productRequestDTO.getQuantity());
        return product;
    }
}
