package com.shaverma.shavermaSite.models.product;

import com.shaverma.shavermaSite.models.baseModel.BaseModel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter @Setter
public class Product extends BaseModel {
    private String productName;
    private double price;

    public Product(@NonNull int classId, String productName, double price) {
        super(classId);
        this.productName = productName;
        this.price = price;
    }
}