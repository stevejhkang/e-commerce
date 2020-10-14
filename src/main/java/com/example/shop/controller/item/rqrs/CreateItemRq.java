package com.example.shop.controller.item.rqrs;

import com.example.shop.domain.values.option.DeliveryOption;
import com.example.shop.domain.values.option.DisplayOption;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
                getterVisibility = JsonAutoDetect.Visibility.NONE,
                isGetterVisibility = JsonAutoDetect.Visibility.NONE)

@Data
@ToString
public class CreateItemRq {
    @NotBlank
    private String itemName;
    @Positive
    private int price;
    private DeliveryOption deliveryOption;
    @NotBlank
    private String description;
    private String manufacturer;
    private DisplayOption displayOption;
    @Positive
    private int stock;

}
