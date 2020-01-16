package com.nguyen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.ws.rs.core.Response;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEntity {

    private Response.Status httpStatusCode;
    private Date date;
    private String content;
}
