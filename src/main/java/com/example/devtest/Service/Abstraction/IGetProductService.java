package com.example.devtest.Service.Abstraction;

import com.example.devtest.DTO.Response.ProductResponseDTO;

import java.util.List;

public interface IGetProductService {
    ProductResponseDTO getProductByName(String name);
    List<ProductResponseDTO> getAllProductsOrderedByPrice();
}
