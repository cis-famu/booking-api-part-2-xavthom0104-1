package com.booking.booking.model;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;


@Data
@NoArgsConstructor

public class Bookings {

    private @Nullable String	bookingID;
    private String userID;
    private Timestamp checkInDate ;
    private Timestamp checkOutDate;
    private long totalPrice;
    private String status;
    private String paymentStatus;
    private @Nullable Timestamp createdAt;


    public Bookings(String bd, String ud, Timestamp ci, Timestamp co,long tp, String s, String ps){
        bookingID = bd;
        userID = ud;
        checkInDate = ci;
        checkOutDate= co;
        totalPrice = tp ;
        status = s;
        paymentStatus = ps;
    }


}