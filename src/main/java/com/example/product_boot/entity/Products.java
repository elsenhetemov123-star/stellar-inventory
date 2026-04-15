package com.example.product_boot.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@SQLRestriction("active = true")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    Double price;

    @CreationTimestamp
    LocalDateTime createAt;

    boolean active = true;
}
