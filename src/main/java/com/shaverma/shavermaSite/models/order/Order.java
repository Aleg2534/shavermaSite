package com.shaverma.shavermaSite.models.order;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shaverma.shavermaSite.models.baseModel.BaseModel;
import com.shaverma.shavermaSite.models.delivery.Delivery;
import com.shaverma.shavermaSite.models.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.plaf.basic.BasicCheckBoxMenuItemUI;
import java.util.Map;
@Getter @Setter @NoArgsConstructor
public class Order extends BaseModel {
    private Basket basket;
    private double sum;
    Delivery delivery;

    public Order(@NonNull int classId, Basket basket, double sum, Delivery delivery) {
        super(classId);
        this.basket = basket;
        this.sum = sum;
        this.delivery = delivery;
    }

    @JsonAnyGetter
    public Basket getBasket() {
        return basket;
    }

//    @Override @JsonValue
//    public String toString() {
//        return "Order{" +
//                "basket=" + basket +
//                ", sum=" + sum +
//                ", delivery=" + delivery +
//                '}';
//    }
}

