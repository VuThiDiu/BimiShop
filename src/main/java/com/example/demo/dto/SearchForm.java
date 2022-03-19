package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

public class SearchForm {
    private Integer costFrom;
    private Integer costTo;
    private String category;
    private String color;

    public SearchForm() {
    }

    public SearchForm(Integer costFrom, Integer costTo, String category, String color) {
        this.costFrom = costFrom;
        this.costTo = costTo;
        this.category = category;
        this.color = color;
    }


    public Integer getCostFrom() {
        return costFrom;
    }

    public Integer getCostTo() {
        return costTo;
    }

    public String getCategory() {
        return category;
    }

    public String getColor() {
        return color;
    }
}
