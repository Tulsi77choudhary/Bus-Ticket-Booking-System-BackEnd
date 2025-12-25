package com.example.OnlineTicket.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StationDTO {
    private Long id;
    private String name;
    private String city;
    private String state;
}
