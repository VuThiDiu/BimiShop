package com.example.demo.model.dto;


import com.example.demo.model.Address;
import lombok.Data;

@Data
public class AddressDTO {
    private String id;
    private String userId;
    private String address;

    public static AddressDTO from (Address address){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setUserId(address.getUser().getId());
        addressDTO.setAddress(address.getAddress());
        return addressDTO;
    }
}
