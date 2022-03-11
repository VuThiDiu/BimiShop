package com.example.demo.model.dto;


import com.example.demo.model.Order;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.List;

@Data
public class OrderDTO {
    private String id;
    private String userId;
    private DateTime orderDate;
    private DateTime shippedDate;
    private List<OrderDetailDTO> orderDetails;
    private String address;
    private int discount;

    public static OrderDTO from (Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUserId(order.getUser().getId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setShippedDate(order.getShippedDate());
        orderDTO.setAddress(order.getAddress());
        orderDTO.setDiscount(order.getDiscount());

        return orderDTO;
    }
}
