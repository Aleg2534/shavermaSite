package com.shaverma.shavermaSite.models.user;

import com.shaverma.shavermaSite.models.baseModel.BaseModel;
import com.shaverma.shavermaSite.models.enums.Roles;
import com.shaverma.shavermaSite.models.order.Order;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class User extends BaseModel {
    private String login;
    private String password;
    private String emailAddress;
    private Order currentOrder;
    private List<Order> orderHistory;
    private Roles role;

    public User(@NonNull int classId
            , String login
            , String password
            , String emailAddress
            , Order currentOrder
            , List<Order> orderHistory
            , Roles role) {
        super(classId);
        this.login = login;
        this.password = password;
        this.emailAddress = emailAddress;
        this.currentOrder = currentOrder;
        this.orderHistory = orderHistory;
        this.role = role;
    }
}
