package com.shaverma.shavermaSite.models.delivery;

import com.shaverma.shavermaSite.models.baseModel.BaseModel;
import com.shaverma.shavermaSite.utils.storage.Storage;
import lombok.*;

@Getter @Setter @NoArgsConstructor @ToString
public class Delivery extends BaseModel {
    private String address;
    private double price;
    private double sum;

    public Delivery(@NonNull int classId, String address, double price, double sum) {
        super(classId);
        this.address = address;
        this.price = price;
        this.sum = sum;
        this.classId= Storage.getDeliveryMap().values().stream().mapToInt(delivery->delivery.classId).max().orElse(Integer.MIN_VALUE);
    }


}
