package com.team2.merchant.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CartProductDTO {

    private String productId;
    private String name;
    private Double price;
    private String image;
    private int quantity;
    private String merchantId;
    private String merchantName;
}
