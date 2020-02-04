package com.team2.merchant.service;

import com.team2.merchant.base.BaseResponse;
import com.team2.merchant.dto.MerchToProdDescDTO;
import com.team2.merchant.dto.MerchantAddProdDTO;
import com.team2.merchant.entity.Product;

import java.util.List;

public interface ProductService {

    boolean addProduct(Product product);
    Product getProduct(String productId);
    List<Product> getAllProducts();
    boolean deleteProduct(String productId);
    List<MerchToProdDescDTO> getProductDescription(String productName);
    BaseResponse<Product> addProduct(MerchantAddProdDTO merchantAddProdDTO);

    Double getProductPrice(String productId);

    Integer setProductRating(int rating,String productId);

    Integer getProductRating(String productId);
}
