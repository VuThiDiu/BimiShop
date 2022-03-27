package com.example.demo.model.dto;

import com.example.demo.model.Cart;
import lombok.Data;

import java.util.List;

@Data

public class CartDTO {
    private String id;
    private UserDTO userDTO;
    private List<ProductDTO> productDTOs;

    public static CartDTO from(Cart  cart){
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        //user
        // listproductDTO
        return cartDTO;
    }

}
