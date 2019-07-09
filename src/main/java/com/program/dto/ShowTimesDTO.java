package com.program.dto;

import java.sql.Timestamp;

import com.program.entity.CinemaRoomEntity;
import com.program.entity.MovieEntity;
import com.program.entity.ShowtimeEntity;

public class ShowTimesDTO {
  private Long showTimeId;

  private Timestamp showDay;

  private Timestamp showDate;

  private long ticketFare;

  private MovieEntity movie;

  private CinemaRoomEntity room;

  public ShowTimesDTO() {
    
  }
  public ShowTimesDTO(final ShowtimeEntity entity) {
    this.showTimeId = entity.getShowTimeId();
    this.showDay = entity.getShowDay();
    this.showDate = entity.getShowDate();
    this.ticketFare = entity.getTicketFare();
    this.movie = entity.getMovie();
    this.room = entity.getRoom();
        
  }
  public ShowtimeEntity convert() {
    final ShowtimeEntity entity = new ShowtimeEntity();
    entity.setShowTimeId(this.showTimeId);
    entity.setShowDay(this.showDay);
    entity.setShowDate(this.showDate);
    entity.setTicketFare(this.ticketFare);
    entity.setMovie(this.movie);
    entity.setRoom(this.room);
    return entity;
  }
  public Long getShowTimeId() {
    return showTimeId;
  }

  public void setShowTimeId(Long showTimeId) {
    this.showTimeId = showTimeId;
  }

  public Timestamp getShowDay() {
    return showDay;
  }

  public void setShowDay(Timestamp showDay) {
    this.showDay = showDay;
  }

  public Timestamp getShowDate() {
    return showDate;
  }

  public void setShowDate(Timestamp showDate) {
    this.showDate = showDate;
  }

  public long getTicketFare() {
    return ticketFare;
  }

  public void setTicketFare(long ticketFare) {
    this.ticketFare = ticketFare;
  }

  public MovieEntity getMovie() {
    return movie;
  }

  public void setMovie(MovieEntity movie) {
    this.movie = movie;
  }

  public CinemaRoomEntity getRoom() {
    return room;
  }

  public void setRoom(CinemaRoomEntity room) {
    this.room = room;
  }
}
