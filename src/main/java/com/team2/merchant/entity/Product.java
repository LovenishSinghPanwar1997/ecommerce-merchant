package com.team2.merchant.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.UUIDGenerator;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity(name = "product")
public class Product {


    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid")
//    @GenericGenerator(name = "uuid",strategy = "uuid2")
    private String productId;
    private String productName;
    private Double productPrice;
    private String productDescription;
    private String usp;
    private int stock;
    private int productSold;
    private int rating;
    private int noOfUsersRated;


    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "merchantId")
    private Merchant merchant;

}
