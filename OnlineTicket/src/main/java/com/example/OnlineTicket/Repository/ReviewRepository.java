package com.example.OnlineTicket.Repository;

import com.example.OnlineTicket.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {


    List<Review> findByBusId(Long busId);


    List<Review> findByPassengerId(Long passengerId);
}
