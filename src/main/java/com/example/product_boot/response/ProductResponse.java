package com.example.product_boot.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {

    Long id;
    String name;
    Double price;
}
