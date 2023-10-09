package com.org.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDtlsRequest {
	
    private String name;
    private String email;
    private String password;
    private String roles;

}
