package com.team2.merchant.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MerchantUpdateProductDetailsDTO {

    private Integer quantity;
    private Double price;
    private String description;

}
