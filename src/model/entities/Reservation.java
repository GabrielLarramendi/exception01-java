package model.entities;


import model.exceptions.DomainException;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Reservation {

    DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private Integer roomNumber;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public Reservation() {
    }

    public Reservation(Integer roomNumber, LocalDate checkIn, LocalDate checkOut) throws DomainException{
        if (!checkOut.isAfter(checkIn)) {
            throw new DomainException("Check-out date must be after check-in date.");
        }
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public Period duration() {
        Period t1 = Period.between(checkIn, checkOut);
        return t1;
    }

    public void updateDate(LocalDate checkIn, LocalDate checkOut) throws DomainException{
        LocalDate now = LocalDate.now();

        if (checkIn.isBefore(now) || checkOut.isBefore(now)) {
            throw  new DomainException("Reservation dates for update must be future dates");
        }
        if (!checkOut.isAfter(checkIn)) {
            throw new DomainException("Check-out date must be after check-in date.");
        }
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return  "Reservation: "
                +"Room "
                + roomNumber
                + ", check-in: "
                + checkIn.format(formattedDate)
                + ", check-out: "
                + checkOut.format(formattedDate)
                + ", "
                + duration().getDays()
                + " nights.";
    }

}
