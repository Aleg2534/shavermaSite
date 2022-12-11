package com.shaverma.shavermaSite.models.order;

import com.shaverma.shavermaSite.models.baseModel.BaseModel;
import com.shaverma.shavermaSite.models.delivery.Delivery;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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

    //    @JsonAnyGetter
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

