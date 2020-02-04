package com.team2.merchant.service.impl;

import com.team2.merchant.base.BaseResponse;
import com.team2.merchant.dto.CartDTO;
import com.team2.merchant.dto.CartProductDTO;
import com.team2.merchant.dto.MerchantToRouterDTO;
import com.team2.merchant.dto.MerchantUpdateProductDetailsDTO;
import com.team2.merchant.entity.Merchant;
import com.team2.merchant.entity.Product;
import com.team2.merchant.repository.MerchantRepository;
import com.team2.merchant.repository.ProductRepository;
import com.team2.merchant.service.MerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public BaseResponse<Merchant> saveMerchant(Merchant merchant) {



        return new BaseResponse<>(merchantRepository.save(merchant));
    }

    @Override
    public Merchant getMerchant(String merchantId) {
        return merchantRepository.findById(merchantId).get();
    }

    @Override
    public List<Merchant> getAllMerchants() {
        return merchantRepository.findAll();
    }

    @Override
    public boolean deleteMerchant(String merchantId) {
        merchantRepository.deleteById(merchantId);
        return true;
    }

    @Override
    public boolean updateMerchantProductdetails(String merchantId, String productId, MerchantUpdateProductDetailsDTO dto) {

        Product product = productRepository.findById(productId).get();
        if(product.getMerchant().getMerchantId().equals(merchantId))
        {
            if(null != dto.getDescription())
            {
                product.setProductDescription(dto.getDescription());
            }
            if(null!= dto.getPrice())
            {
                product.setProductPrice(dto.getPrice());
            }
            if(null!=dto.getQuantity())
            {
                product.setStock(dto.getQuantity());
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public BaseResponse<MerchantToRouterDTO> getMerchantInfo(String merchantId) {
        Merchant merchant = merchantRepository.findById(merchantId).get();
        MerchantToRouterDTO merchantToRouterDTO = new MerchantToRouterDTO();
        BeanUtils.copyProperties(merchant,merchantToRouterDTO);
        merchantToRouterDTO.setProductsOfMerchant(merchant.getProducts());


        return new BaseResponse<>(merchantToRouterDTO);
    }

    @Override
    public Boolean buyNow(CartDTO cartDTO) {
        List<CartProductDTO> cartProductDTOList = cartDTO.getProductsBought();
        for(CartProductDTO cartProductDTO : cartProductDTOList) {
            Merchant merchant = merchantRepository.findById(cartProductDTO.getMerchantId()).get();
            List<Product> products = merchant.getProducts();
            List<Product> productList = products.stream().filter(product -> product.getProductId()
                    .equals(cartProductDTO.getProductId())).collect(Collectors.toList());
            if (productList.size() > 0) {
                Product product = productList.get(0);
                if (product.getStock() < cartProductDTO.getQuantity()) {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        for(CartProductDTO cartProductDTO : cartProductDTOList){
            Merchant merchant = merchantRepository.findById(cartProductDTO.getMerchantId()).get();
            int totalQuantity = merchant.getNoOfProductsSold();
            List<Product> products = merchant.getProducts();
            List<Product> productList = products.stream().filter(product -> product.getProductId()
                    .equals(cartProductDTO.getProductId())).collect(Collectors.toList());
            if (productList.size() > 0) {
                Product product = productList.get(0);
                if (product.getStock() < cartProductDTO.getQuantity()) {
                    totalQuantity += cartProductDTO.getQuantity();
                    product.setStock(product.getStock()- cartProductDTO.getQuantity());
                    product.setProductSold(product.getProductSold()+cartProductDTO.getQuantity());
                }
            }
            merchant.setNoOfProductsSold(totalQuantity);
        }
        return true;
    }

    private List<Merchant> getBestMerchant(List<Product> products){
        List<Merchant> merchantList = products.stream().map(Product::getMerchant).collect(Collectors.toList());
        List<Merchant> merchantWithRating = new ArrayList<>();
        for(Merchant merchant : merchantList){
            int noOfProducts = merchant.getProducts().size();
            int noOfProductsSold = merchant.getNoOfProductsSold();
            int totalStock = 0;
            int productsTotalRating = 0;
            int productStock = 0;
            Double priceOfProduct = 0d;
            for(Product product:merchant.getProducts()){
                totalStock += product.getStock();
                productsTotalRating += product.getRating();
                if(products.contains(product)){
                    productStock = product.getStock();
                    priceOfProduct = product.getProductPrice();
                }
            }
            productsTotalRating = productsTotalRating/noOfProducts;
            double merchantRating = (2*(noOfProductsSold + productsTotalRating )
                    + noOfProducts + totalStock + productStock + priceOfProduct)/8;
            merchant.setMerchantRating(merchantRating);
            merchantWithRating.add(merchant);
        }
        List<Merchant> sortedMerchant = merchantWithRating.stream().sorted(Comparator.comparing(
                Merchant::getMerchantRating)).collect(Collectors.toList());
        return sortedMerchant;
    }
}
