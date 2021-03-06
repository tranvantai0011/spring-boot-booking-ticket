package com.program.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name = "MovieTicket")
public class MovieTicketEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ticketId")
  private Long ticketId;

  @Column(name = "totalAmount")
  private Long totalAmount;

  @Column(name = "bookingDate")
  private Timestamp bookingDate;

  @OneToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "seatId", nullable = true)
  private SeatEntity seat;

  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "userId", nullable = true)
  private UserEntity user;

  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "showtimeId", nullable = true)
  private ShowtimeEntity show;

  public Long getTicketId() {
    return ticketId;
  }

  public void setTicketId(Long ticketId) {
    this.ticketId = ticketId;
  }

  public long getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(long totalAmount) {
    this.totalAmount = totalAmount;
  }

  public Timestamp getBookingDate() {
    return bookingDate;
  }

  public void setBookingDate(Timestamp bookingDate) {
    this.bookingDate = bookingDate;
  }

  public SeatEntity getSeat() {
    return seat;
  }

  public MovieTicketEntity setSeat(SeatEntity seat) {
    this.seat = seat;
    return this;
  }

  public UserEntity getUser() {
    return user;
  }

  public MovieTicketEntity setUser(UserEntity user) {
    this.user = user;
    return this;
  }

  public ShowtimeEntity getShow() {
    return show;
  }

  public MovieTicketEntity setShow(ShowtimeEntity show) {
    this.show = show;
    return this;
  }

}
