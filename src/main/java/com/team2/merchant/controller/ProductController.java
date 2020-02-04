package com.team2.merchant.controller;

import com.team2.merchant.base.BaseResponse;
import com.team2.merchant.dto.MerchantAddProdDTO;
import com.team2.merchant.entity.Merchant;
import com.team2.merchant.entity.Product;
import com.team2.merchant.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/merchant-product")
@CrossOrigin(origins = {"*"})
public class ProductController {

    @Autowired
    ProductService productService;


    @PostMapping("/add")
    public BaseResponse<Product> addProduct(@RequestBody MerchantAddProdDTO merchantAddProdDTO)
    {
        return productService.addProduct(merchantAddProdDTO);
    }

    @GetMapping("/getAll")
    public BaseResponse<List<Product>> getAllProduct(){
        List<Product> productList = productService.getAllProducts();
        productList.get(0).getMerchant().getMerchantId();
        return new BaseResponse<>(productList);
    }

    @GetMapping("/getProductById/{productId}")
    public Product getProductById(@PathVariable("productId") String productId)
    {
       return productService.getProduct(productId);
    }

    @GetMapping("/getProductPrice/{productId}")
    public Double getProductPrice(@PathVariable("productId") String productId)
    {
        return productService.getProductPrice(productId);
    }

    @GetMapping("/setRating/{rating}/{productId}")
    Integer setProductRating(@PathVariable("rating") int rating,@PathVariable("productId") String productId)
    {
        return productService.setProductRating(rating,productId);
    }

    @GetMapping("/getRating/{productId}")
    Integer getProductRating(@PathVariable("productId") String productId)
    {
        return productService.getProductRating(productId);
    }

}
