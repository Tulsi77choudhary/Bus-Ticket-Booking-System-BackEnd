package com.example.OnlineTicket;

import com.example.OnlineTicket.model.Bus;
import com.example.OnlineTicket.Repository.BusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SeatControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private BusRepository busRepository;

    @BeforeEach
    public void setupBus(){
        String busNumber = "MP50MD7578";
        if(busRepository.findByBusNumber(busNumber).isEmpty()){
            Bus bus  = new Bus();
            bus.setBusNumber("MP50MD7578");
            bus.setTotalSeats(35);
            busRepository.save(bus);
        }
    }

    @Test
    public void testGetSeatsValidBusNumber() throws  Exception {
        mockMvc.perform(get("/api/seats/bus/MP50MD7578"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
