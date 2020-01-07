package com.nguyen.ws;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "user")
public class User {

    private Integer id;
    private String username;

    public void setData(User user) {
        this.username = user.username;
    }
}
