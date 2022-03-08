package com.example.demo.model.dto;


import com.example.demo.model.OrderDetail;
import lombok.Data;

@Data
public class OrderDetailDTO {
    private String id;
    private String orderId;
    private String productId;
    private double rating;
    private String comment;

    public static  OrderDetailDTO from (OrderDetail  orderDetail){
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        orderDetailDTO.setId(orderDetail.getId());
        orderDetailDTO.setOrderId(orderDetail.getOrder().getId());
        orderDetailDTO.setProductId(orderDetail.getProduct().getId());
        orderDetailDTO.setRating(orderDetail.getRating());
        orderDetailDTO.setComment(orderDetail.getComment());
        return orderDetailDTO;
    }
}
