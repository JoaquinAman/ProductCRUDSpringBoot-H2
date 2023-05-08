package com.example.devtest.Service.Abstraction;

import com.example.devtest.DTO.Request.ProductRequestDTO;
import com.example.devtest.DTO.Response.ProductResponseDTO;

public interface IUpdateProductService {
    ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO);
}
