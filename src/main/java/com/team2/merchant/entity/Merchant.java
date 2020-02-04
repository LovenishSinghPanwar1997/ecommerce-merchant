package com.team2.merchant.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.UUIDGenerator;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "Merchant")
public class Merchant {

    @Id
    private String merchantId;
    private  String merchantName;
    private String profileImage;
    private Integer noOfProductsSold;
    private String address;
    private String emailId;
    private String phoneNumber;
    private Double merchantRating;

    @JsonBackReference
    @ToString.Exclude
    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Product> products;

}
