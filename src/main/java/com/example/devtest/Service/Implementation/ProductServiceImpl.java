package com.example.devtest.Service.Implementation;

import com.example.devtest.DTO.Request.ProductRequestDTO;
import com.example.devtest.DTO.Response.ProductResponseDTO;
import com.example.devtest.Model.Product;
import com.example.devtest.Repository.IProductRepository;
import com.example.devtest.Service.Abstraction.ICreateProductService;
import com.example.devtest.Service.Abstraction.IDeleteProductService;
import com.example.devtest.Service.Abstraction.IGetProductService;
import com.example.devtest.Service.Abstraction.IUpdateProductService;
import com.example.devtest.Utils.ConvertUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ICreateProductService, IDeleteProductService, IGetProductService, IUpdateProductService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ConvertUtils convertUtils;
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = convertUtils.convertToProduct(productRequestDTO);
        product = productRepository.save(product);
        ProductResponseDTO productResponseDTO = convertUtils.convertToProductResponseDTO(product);
        return productResponseDTO;
    }

    @Override
    public void deleteProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Product with id: " + id + " not found");
        }
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
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

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO) {
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
}
