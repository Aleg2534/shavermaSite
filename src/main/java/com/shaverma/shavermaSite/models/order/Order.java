package com.shaverma.shavermaSite.models.order;

import com.shaverma.shavermaSite.models.baseModel.BaseModel;
import com.shaverma.shavermaSite.models.delivery.Delivery;
import com.shaverma.shavermaSite.models.product.Product;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Map;
@Getter @Setter
public class Order extends BaseModel {
    private Map<Product,Integer> basket;
    private double sum;
    Delivery delivery;

    public Order(@NonNull int classId, Map<Product, Integer> basket, double sum, Delivery delivery) {
        super(classId);
        this.basket = basket;
        this.sum = sum;
        this.delivery = delivery;
    }
}
