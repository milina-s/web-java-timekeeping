package com.example.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Activity {

    private Long id;
    private String name;
    private Long category_id;
    private int duration;
}
