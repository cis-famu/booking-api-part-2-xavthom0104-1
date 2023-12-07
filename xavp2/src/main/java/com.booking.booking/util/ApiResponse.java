package com.booking.booking.util;

public record ApiResponse(boolean success, String message, Object data, Object error) {

        }

