package com.team2.merchant.dto.searchClientDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSearchClientDTO {
    private String productId;
    private String productName;
    private Double productPrice;
    private String productImage;
    private String productDescription;
    private String usp;
    private String attributes;
    private String categories;
    private Integer quantity;
    private String merchantId;

}
