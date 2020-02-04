package com.team2.merchant.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MerchToProdDescDTO {

    private String merchantName;
    private Double productPrice;
    private Double merchantRating;
    private String productId;

}
