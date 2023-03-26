package com.shaverma.shavermaSite.models.order;

import com.shaverma.shavermaSite.utils.storage.Storage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.shaverma.shavermasite.utils.storage");
//        applicationContext.refresh();
        Storage storage = applicationContext.getBean(Storage.class);
        return basket.get(productId) * storage.getProductMap().get(productId).getPrice();
    }

    public double finalSum()
    {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.shaverma.shavermasite.utils.storage");
//        applicationContext.refresh();
        Storage storage = applicationContext.getBean(Storage.class);
        double sum=0;
        for(int i: basket.keySet())
        {
            sum+=basket.get(i)*storage.getProductMap().get(i).getPrice();
        }
        return sum;
    }
}
