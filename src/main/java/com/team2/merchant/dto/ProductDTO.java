package com.team2.merchant.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductDTO {


    private String productId;
    private Double productPrice;
    private String productDescription;
    private String usp;
    private Integer stock;
    private Integer productSold;




}
