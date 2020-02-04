package com.team2.merchant.service;

import com.team2.merchant.base.BaseResponse;
import com.team2.merchant.dto.CartDTO;
import com.team2.merchant.dto.MerchantToRouterDTO;
import com.team2.merchant.dto.MerchantUpdateProductDetailsDTO;
import com.team2.merchant.entity.Merchant;

import java.util.List;

public interface MerchantService {

    BaseResponse<Merchant> saveMerchant(Merchant merchant);
    Merchant getMerchant(String merchantId);
    List<Merchant> getAllMerchants();
    boolean deleteMerchant(String merchantId);

    boolean updateMerchantProductdetails(String merchantId, String productId, MerchantUpdateProductDetailsDTO dto);


    BaseResponse<MerchantToRouterDTO> getMerchantInfo(String merchantId);

    Boolean buyNow(CartDTO cartDTO);
}
