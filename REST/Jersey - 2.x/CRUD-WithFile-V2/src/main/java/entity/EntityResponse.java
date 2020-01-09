package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.ws.rs.core.Response;

@Data
@AllArgsConstructor
@Builder
public class EntityResponse {

    private Integer HttpStatus;
    private String message;
}
