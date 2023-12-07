package com.booking.booking.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentInformation {

    private String cardnumber;
    private String expirationDate;
    private String billingaddress;

}
