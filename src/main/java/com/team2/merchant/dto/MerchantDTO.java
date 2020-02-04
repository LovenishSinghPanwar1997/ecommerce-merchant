package com.team2.merchant.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MerchantDTO {

    private String merchantId;
    private  String merchantName;
    private List<ProductDTO> products;
    private String profileImage;
    private Integer noOfProductsSold;
    private String address;
    private String emailId;
    private String phoneNumber;
    private Double merchantRating;

}
