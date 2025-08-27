package com.iiyatsu.pojo.user;

import lombok.Data;

@Data
public class User {
    private String id;
    private String name;
    private String passwd;
    private boolean admin = false;
    private boolean userStatic = false;
}
