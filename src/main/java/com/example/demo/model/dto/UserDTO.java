package com.example.demo.model.dto;

import com.example.demo.model.User;
import lombok.Data;
import org.nd4j.linalg.api.ops.impl.reduce.same.Prod;


import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDTO {
    private String id;
    private String username;
    private String phoneNumber;
    private String defaultAddress;
    private List<AddressDTO> addresses ;
    private List<ProductDTO> products;
    private List<OrderDTO> orders;
    public static UserDTO from (User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setDefaultAddress(user.getDefaultAddress());
        userDTO.setAddresses(user.getAddresses().stream().map(AddressDTO::from).collect(Collectors.toList()));
        userDTO.setProducts(user.getProducts().stream().map(ProductDTO::from).collect(Collectors.toList()));
        userDTO.setOrders(user.getOrders().stream().map(OrderDTO::from).collect(Collectors.toList()));

        return userDTO;
    }
}
