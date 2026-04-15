package com.example.product_boot.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreateRequest {

    @NotBlank(message = "Name is required")
    String name;
    @NotBlank(message = "Price is required")
    Double price;


}
