package com.team2.merchant.service.impl;

import com.team2.merchant.Client.SearchClient;
import com.team2.merchant.base.BaseResponse;
import com.team2.merchant.dto.MerchToProdDescDTO;
import com.team2.merchant.dto.MerchantAddProdDTO;
import com.team2.merchant.dto.searchClientDTO.ProductSearchClientDTO;
import com.team2.merchant.entity.Product;
import com.team2.merchant.repository.MerchantRepository;
import com.team2.merchant.repository.ProductRepository;
import com.team2.merchant.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    SearchClient searchClient;

    @Override
    public boolean addProduct(Product product) {
        productRepository.save(product);
        return false;
    }

    @Override
    public Product getProduct(String productId) {
        return productRepository.findById(productId).get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public boolean deleteProduct(String productId) {
        productRepository.deleteById(productId);
        return true;
    }

    @Override
    public List<MerchToProdDescDTO> getProductDescription(String productName) {

        List<Product> products = productRepository.findByProductName(productName);
        List<MerchToProdDescDTO> finalList = new ArrayList<>();
        for (Product product:products) {
            MerchToProdDescDTO merchToProdDescDTO = new MerchToProdDescDTO();
            merchToProdDescDTO.setMerchantName(merchantRepository.findById(product.getMerchant().getMerchantId()).get().getMerchantName());
            merchToProdDescDTO.setMerchantRating(merchantRepository.findById(product.getMerchant().getMerchantId()).get().getMerchantRating());
            merchToProdDescDTO.setProductId(product.getProductId());
            merchToProdDescDTO.setProductPrice(product.getProductPrice());
            finalList.add(merchToProdDescDTO);
        }

        return finalList;
    }


    @Override
    public BaseResponse<Product> addProduct(MerchantAddProdDTO merchantAddProdDTO) {
        Product product = new Product();
        product.setMerchant(merchantRepository.findById(merchantAddProdDTO.getMerchantId()).get());
        product.setProductDescription(merchantAddProdDTO.getProductDescription());
        product.setProductPrice(merchantAddProdDTO.getProductPrice());
        product.setProductId(merchantAddProdDTO.getProductId());
        product.setStock(merchantAddProdDTO.getQuantity());
        product.setUsp(merchantAddProdDTO.getUsp());
        product.setProductName(merchantAddProdDTO.getProductName());
        product.setProductSold(0);
        product.setNoOfUsersRated(0);
        product.setRating(0);

        ProductSearchClientDTO productSearchClientDTO = new ProductSearchClientDTO();
        productSearchClientDTO.setAttributes(merchantAddProdDTO.getAttributes().toString());
        productSearchClientDTO.setCategories(merchantAddProdDTO.getCategories().toString());
        productSearchClientDTO.setMerchantId(merchantAddProdDTO.getMerchantId());
        productSearchClientDTO.setProductDescription(merchantAddProdDTO.getProductDescription());
        productSearchClientDTO.setProductId(merchantAddProdDTO.getProductId());
        productSearchClientDTO.setProductName(merchantAddProdDTO.getProductName());
        productSearchClientDTO.setProductPrice(merchantAddProdDTO.getProductPrice());
        productSearchClientDTO.setQuantity(merchantAddProdDTO.getQuantity());
        productSearchClientDTO.setUsp(merchantAddProdDTO.getUsp());
        productSearchClientDTO.setProductImage(merchantAddProdDTO.getImages().get(0));

        searchClient.addProductInSearchDb(productSearchClientDTO);

        return new BaseResponse<>(productRepository.save(product));
    }

    @Override
    public Double getProductPrice(String productId) {
        return productRepository.findById(productId).get().getProductPrice();
    }

    @Override
    public Integer setProductRating(int rating,String productId) {
            Product product = productRepository.findById(productId).get();
            int currRating = product.getRating();
            int currNoOfCustomers = product.getNoOfUsersRated();
            currRating = currRating + rating;
            currNoOfCustomers = currNoOfCustomers +1;
            int updatedRating = currRating/currNoOfCustomers;
            product.setRating(updatedRating);
            productRepository.save(product);
            return updatedRating;

    }

    @Override
    public Integer getProductRating(String productId) {
        return productRepository.findById(productId).get().getRating();
    }
}
