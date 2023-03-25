package com.shaverma.shavermaSite.models.order;

import com.shaverma.shavermaSite.models.baseModel.BaseModel;
import com.shaverma.shavermaSite.models.delivery.Delivery;
import com.shaverma.shavermaSite.utils.storage.Storage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class Order extends BaseModel {
    private Basket basket;
    private double sum;
    Delivery delivery;

    public Order(@NonNull int classId, Basket basket, double sum, Delivery delivery) {
        super(classId);
        this.classId = Storage.getOrderMap().values().stream().mapToInt(order -> order.classId).max().orElse(Integer.MIN_VALUE);
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

