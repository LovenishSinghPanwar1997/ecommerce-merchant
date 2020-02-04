package com.team2.merchant.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CartDTO {
    private String customerId;
    private String customerEmailId;
    private List<CartProductDTO> productsBought;
    private Date dateOfPlacingOrder;
    private String address;
}
