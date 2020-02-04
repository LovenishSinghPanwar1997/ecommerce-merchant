package com.team2.merchant.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MerchantAddProdDTO {

    private String productId;
    private String productName;
    private Double productPrice;
    private String productDescription;
    private Map<String,String> attributes;
    private String usp;
    private List<String> categories;
    private List<String> images;
    private Integer quantity;
    private String merchantId;

}
