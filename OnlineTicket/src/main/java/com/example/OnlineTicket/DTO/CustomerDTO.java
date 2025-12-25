package com.example.OnlineTicket.DTO;

import lombok.Data;
import org.hibernate.annotations.processing.Pattern;

@Data
public class CustomerDTO {
    private Long id;
    private String fullName;
    private String phone;
    private String email;

}
