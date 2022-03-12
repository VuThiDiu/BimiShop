package com.example.demo.dto;


import lombok.Data;

@Data
public class TagResponse {
    private String imageUrl;
    private String tagCategory;
    private String tagColor;

    public TagResponse() {
    }

    public TagResponse(String tagCategory, String tagColor) {
        this.tagCategory = tagCategory;
        this.tagColor = tagColor;
    }
}
