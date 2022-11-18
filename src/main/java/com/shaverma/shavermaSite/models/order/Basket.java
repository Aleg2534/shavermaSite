package com.shaverma.shavermaSite.models.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Basket {
    Map<Integer, Integer> basket;

    public Map<Integer, Integer> getBasket() {
        return basket;
    }
}
