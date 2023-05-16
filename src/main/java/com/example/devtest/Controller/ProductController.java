package com.example.devtest.Controller;


import com.example.devtest.DTO.Request.ProductRequestDTO;
import com.example.devtest.DTO.Response.ProductResponseDTO;
import com.example.devtest.Service.Abstraction.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ICRUDService crudService;
    private final IGetProductService getProductService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponseDTO = (ProductResponseDTO) crudService.create(productRequestDTO);
        return ResponseEntity.ok(productResponseDTO);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        ProductResponseDTO productResponseDTO = (ProductResponseDTO) crudService.getById(id);
        return ResponseEntity.ok(productResponseDTO);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<ProductResponseDTO> getProductByName(@PathVariable String name) {
        ProductResponseDTO productResponseDTO = getProductService.getProductByName(name);
        return ResponseEntity.ok(productResponseDTO);
    }
    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDTO>> getAllProductsOrderedByPrice() {
        List<ProductResponseDTO> productsResponseDTO = getProductService.getAllProductsOrderedByPrice();
        return ResponseEntity.ok(productsResponseDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        crudService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponseDTO = (ProductResponseDTO) crudService.update(id, productRequestDTO);
        return ResponseEntity.ok(productResponseDTO);
    }
}
