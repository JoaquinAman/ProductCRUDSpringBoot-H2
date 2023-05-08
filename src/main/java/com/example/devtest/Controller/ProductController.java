package com.example.devtest.Controller;


import com.example.devtest.DTO.Request.ProductRequestDTO;
import com.example.devtest.DTO.Response.ProductResponseDTO;
import com.example.devtest.Service.Abstraction.ICreateProductService;
import com.example.devtest.Service.Abstraction.IDeleteProductService;
import com.example.devtest.Service.Abstraction.IGetProductService;
import com.example.devtest.Service.Abstraction.IUpdateProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ICreateProductService createProductService;
    @Autowired
    private IGetProductService getProductService;
    @Autowired
    private IDeleteProductService deleteProductService;
    @Autowired
    private IUpdateProductService updateProductService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponseDTO = createProductService.createProduct(productRequestDTO);
        return ResponseEntity.ok(productResponseDTO);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        ProductResponseDTO productResponseDTO = getProductService.getProductById(id);
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
        deleteProductService.deleteProductById(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponseDTO = updateProductService.updateProduct(id, productRequestDTO);
        return ResponseEntity.ok(productResponseDTO);
    }
}
