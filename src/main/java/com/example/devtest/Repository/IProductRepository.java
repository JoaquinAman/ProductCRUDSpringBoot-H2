package com.example.devtest.Repository;

import com.example.devtest.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
    List<Product> findAllByOrderByPriceAsc();
}
