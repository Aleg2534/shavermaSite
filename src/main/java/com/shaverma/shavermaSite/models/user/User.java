package com.shaverma.shavermaSite.models.user;

import com.shaverma.shavermaSite.models.baseModel.BaseModel;
import com.shaverma.shavermaSite.models.enums.Roles;
import com.shaverma.shavermaSite.models.order.Order;
import com.shaverma.shavermaSite.utils.storage.Storage;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
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
//        this.classId= Storage.getUserMap().values().stream().mapToInt(user->user.classId).max().orElse(Integer.MIN_VALUE);
    }

    @Override
    public User clone() {
        return new User(classId, login, password, emailAddress, currentOrder, orderHistory, role);
    }
}
