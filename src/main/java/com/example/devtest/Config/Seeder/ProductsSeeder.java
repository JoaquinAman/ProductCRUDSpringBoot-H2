package com.example.devtest.Config.Seeder;


import com.example.devtest.Model.Product;
import com.example.devtest.Repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductsSeeder implements CommandLineRunner {

    private final IProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        loadProducts();
    }
    private void loadProducts() {
        productRepository.save(Product.builder()
                .name("Product 1")
                .description("Description 1")
                .price(5)
                .quantity(5)
                .build()
        );
        productRepository.save(Product.builder()
                .name("Product 2")
                .description("Description 2")
                .price(1)
                .quantity(1)
                .build()
        );
        productRepository.save(Product.builder()
                .name("Product 3")
                .description("Description 3")
                .price(4)
                .quantity(4)
                .build()
        );
        productRepository.save(Product.builder()
                .name("Product 4")
                .description("Description 4")
                .price(2)
                .quantity(2)
                .build()
        );
        productRepository.save(Product.builder()
                .name("Product 5")
                .description("Description 5")
                .price(3)
                .quantity(3)
                .build()
        );
    }

}
