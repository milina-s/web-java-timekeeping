package com.example.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Request {

    private Long id;
    private Long activity_id;
    private Long user_id;
    private RequestStatus status;
    private RequestType type;

}
