package com.example.OnlineTicket.Service;

import com.example.OnlineTicket.DTO.BusRequest;
import com.example.OnlineTicket.DTO.BusResponseDTO;
import com.example.OnlineTicket.DTO.CreateBus;
import com.example.OnlineTicket.Excaption.ResourceNotFoundException;
import com.example.OnlineTicket.Repository.BookingRepository;
import com.example.OnlineTicket.Repository.BusRepository;
import com.example.OnlineTicket.model.Booking;
import com.example.OnlineTicket.model.BookingStatus;
import com.example.OnlineTicket.model.Bus;
import com.example.OnlineTicket.model.BusStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusServiceImpl implements BusService{
    @Autowired
    private BusRepository  busRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Bus createBus(CreateBus request) {

        if (busRepository.existsByBusNumber(request.getBusNumber())) {
            throw new RuntimeException("Bus with this number already exists");
        }
       Bus bus = new Bus();
       bus.setBusName(request.getBusName());
       bus.setBusNumber(request.getBusNumber());
       bus.setSource(request.getSource());
       bus.setDestination(request.getDestination());
       bus.setBusType(request.getBusType());
       bus.setTotalSeats(request.getTotalSeats());
       bus.setTime(request.getTime());
       bus.setDate(request.getDate());

       return busRepository.save(bus);
    }

    @Override
    public List<Bus> searchBuses(BusRequest request) {
        List<Bus> buses = busRepository.findBySourceAndDestinationAndTravelDate(
                request.getBusNumber(),
                request.getSource(),
                request.getTravelDate()
        );
        if (buses.isEmpty()){
            throw new RuntimeException("No buses found");
        }
        return buses;
    }


    @Override
    public BusResponseDTO updateBus(String busNumber, CreateBus busDTO) {
        Bus bus = busRepository.findByBusNumber(busNumber)
                .orElseThrow(()-> new ResourceNotFoundException("Bus not found with number: " + busNumber));

        bus.setSource(busDTO.getSource());
        bus.setDestination(busDTO.getDestination());
        bus.setDate(busDTO.getDate());
        bus.setTime(busDTO.getTime());
        bus.setTotalSeats(busDTO.getTotalSeats());

        Bus updatedBus = busRepository.save(bus);

        return new BusResponseDTO(
                updatedBus.getId(),
                updatedBus.getBusNumber(),
                updatedBus.getSource(),
                updatedBus.getDestination(),
                updatedBus.getTotalSeats()
        );

    }


    @Override
    public List<Bus> getAllBuses() {
       return busRepository.findAll();

    }
    @Override
    public void deleteBus(String busNumber) {
        Bus bus = busRepository.findByBusNumber(busNumber)
                .orElseThrow(()-> new RuntimeException("Bus not found with ID:"));
        busRepository.delete(bus);
    }

    @Override
    @Transactional
    public void cancelBus(Long busId) {

        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        bus.setStatus(BusStatus.CANCELLED);


        List<Booking> bookings = bookingRepository.findByBus(bus);
        for (Booking booking : bookings) {
            booking.setStatus(BookingStatus.CANCELLED);
        }

        busRepository.save(bus);
    }


}
