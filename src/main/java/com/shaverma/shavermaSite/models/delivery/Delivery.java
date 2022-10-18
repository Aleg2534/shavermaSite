package com.shaverma.shavermaSite.models.delivery;

import com.shaverma.shavermaSite.models.baseModel.BaseModel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter @Setter
public class Delivery extends BaseModel {
    private String address;
    private double price;
    private double sum;

    public Delivery(@NonNull int classId, String address, double price, double sum) {
        super(classId);
        this.address = address;
        this.price = price;
        this.sum = sum;
    }
}
