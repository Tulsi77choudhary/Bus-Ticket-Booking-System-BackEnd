package com.example.OnlineTicket.Repository;

import com.example.OnlineTicket.model.BookingReview;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingReviewRepository extends JpaRepository<BookingReview,Long> {


    List<BookingReview> findByBusId(Long busId);


    List<BookingReview> findByPassengerId(Long passengerId);
}
