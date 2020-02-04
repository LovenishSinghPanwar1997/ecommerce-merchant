package com.team2.merchant.controller;

import com.team2.merchant.base.BaseResponse;
import com.team2.merchant.dto.CartDTO;
import com.team2.merchant.dto.MerchantToRouterDTO;
import com.team2.merchant.dto.ProductDTO;
import com.team2.merchant.entity.Merchant;
import com.team2.merchant.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/merchant")
@CrossOrigin(origins = {"*"})
public class MerchantController {

    @Autowired
    MerchantService merchantService;

    @PostMapping("/add")
    public BaseResponse<Merchant> save(@RequestBody Merchant merchant){
        return merchantService.saveMerchant(merchant);
    }

    @GetMapping("/getInfo/{merchantId}")
    public BaseResponse<MerchantToRouterDTO> getMerchantInfo(@PathVariable("merchantId") String merchantId){
        return merchantService.getMerchantInfo(merchantId);
    }

    @GetMapping("/hello")
    public String trial()
{
    return "hello";
}

    @PutMapping("/buyNow")
    public Boolean buyNow(@RequestBody CartDTO cartDTO){
        return merchantService.buyNow(cartDTO);
    }
}
