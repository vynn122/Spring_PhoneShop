package org.zin.com.phoneshopapi.entity;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String imageUrl;

    private String publicId;
    private boolean thumbnail;
    private int displayOrder;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}

