package com.shaverma.shavermaSite.models.product;

import com.shaverma.shavermaSite.models.baseModel.BaseModel;
import com.shaverma.shavermaSite.utils.storage.Storage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.apache.catalina.mapper.Mapper;

@Getter @Setter @NoArgsConstructor
public class Product extends BaseModel {
    private String productName;
    private double price;

    public Product(@NonNull int classId, String productName, double price) {
        super(classId);
        this.productName = productName;
        this.price = price;
//        this.classId= Storage.getProductMap().values().stream().mapToInt(product->product.classId).max().orElse(Integer.MIN_VALUE);
    }
}