package com.company;



import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        LocalDateTime tiempo_limiteCheckIn=LocalDateTime.now();
        tiempo_limiteCheckIn.withHour(10);

        System.out.println(tiempo_limiteCheckIn.getHour());
    }
}
