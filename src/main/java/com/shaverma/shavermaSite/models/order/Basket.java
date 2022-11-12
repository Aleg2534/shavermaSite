package com.shaverma.shavermaSite.models.order;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Basket {
    @JsonValue
    private HashMap<Integer,Integer> basket;

    public Basket(HashMap<Integer, Integer> basket) {
        this.basket = basket;
    }
}
