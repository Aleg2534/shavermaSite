package com.shaverma.shavermaSite.models.order;

import com.shaverma.shavermaSite.utils.storage.Storage;
import lombok.AllArgsConstructor;
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

    public double sumProduct(int productId) {
        return basket.get(productId) * Storage.getProductMap().get(productId).getPrice();
    }

    public double finalSum()
    {
        double sum=0;
        for(int i: basket.keySet())
        {
            sum+=basket.get(i)*Storage.getProductMap().get(i).getPrice();
        }
        return sum;
    }
}
