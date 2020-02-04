package com.team2.merchant.repository;

import com.team2.merchant.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {

List<Product> findByProductName(String productName);


}
