package com.example.devtest.Service.Implementation;

import com.example.devtest.DTO.Request.ProductRequestDTO;
import com.example.devtest.DTO.Response.ProductResponseDTO;
import com.example.devtest.Model.Product;
import com.example.devtest.Repository.IProductRepository;
import com.example.devtest.Service.Abstraction.*;
import com.example.devtest.Utils.ConvertUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IGetProductService, ICRUDService<ProductResponseDTO, ProductRequestDTO> {
    private final IProductRepository productRepository;

    private final ConvertUtils convertUtils;

    @Override
    public ProductResponseDTO create(ProductRequestDTO productRequestDTO) {
        Product product = convertUtils.convertToProduct(productRequestDTO);
        product = productRepository.save(product);
        ProductResponseDTO productResponseDTO = convertUtils.convertToProductResponseDTO(product);
        return productResponseDTO;
    }
    @Override
    public ProductResponseDTO update(Long id, ProductRequestDTO productRequestDTO) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product productToUpdate = product.get();
            productToUpdate.setName(productRequestDTO.getName());
            productToUpdate.setDescription(productRequestDTO.getDescription());
            productToUpdate.setPrice(productRequestDTO.getPrice());
            productToUpdate.setQuantity(productRequestDTO.getQuantity());
            productToUpdate = productRepository.save(productToUpdate);
            ProductResponseDTO productResponseDTOToUpdate = convertUtils.convertToProductResponseDTO(productToUpdate);
            return productResponseDTOToUpdate;
        }
        throw new EntityNotFoundException("Product with id: " + id + " not found");
    }
    @Override
    public List<ProductResponseDTO> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductResponseDTO> productResponseDTOS = products
                .stream()
                .map(product ->
                        convertUtils.convertToProductResponseDTO(product))
                .toList();
        if(productResponseDTOS.isEmpty())
            throw new EntityNotFoundException("No products found");
        return productResponseDTOS;
    }



    @Override
    public void deleteById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Product with id: " + id + " not found");
        }
    }

    @Override
    public ProductResponseDTO getById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            ProductResponseDTO productResponseDTO = convertUtils.convertToProductResponseDTO(product.get());
            return productResponseDTO;
        }
        throw new EntityNotFoundException("Product with id: " + id + " not found");
    }

    @Override
    public ProductResponseDTO getProductByName(String name) {
        Product product = productRepository.findByName(name);
        if (product != null) {
            ProductResponseDTO productResponseDTO = convertUtils.convertToProductResponseDTO(product);
            return productResponseDTO;
        }

        throw new EntityNotFoundException("Product with id: " + name + " not found");
    }

    @Override
    public List<ProductResponseDTO> getAllProductsOrderedByPrice() {
        List<Product> products = productRepository.findAllByOrderByPriceAsc();
        List<ProductResponseDTO> productResponseDTOS = products
                .stream()
                .map(product ->
                convertUtils.convertToProductResponseDTO(product))
                .toList();
        if(productResponseDTOS.isEmpty())
            throw new EntityNotFoundException("No products found");
        return productResponseDTOS;
    }
}
