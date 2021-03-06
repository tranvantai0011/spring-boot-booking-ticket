package com.program.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.program.conmmon.RestContant;
import com.program.dto.CinemaDTO;
import com.program.error.ResponseExceptionModel;
import com.program.service.CinemaService;

@RestController
@RequestMapping("/rest/api/cinema")
public class CinemaRestController {

  @Autowired
  private CinemaService cinemaService;

  @GetMapping(value = RestContant.REST_ALL)
  public @ResponseBody List<CinemaDTO> cinemas() {
    List<CinemaDTO> cinemaDTOs = cinemaService.findAll();
    return cinemaDTOs;
  }

  @GetMapping(value = RestContant.REST_BY_ID)
  public @ResponseBody CinemaDTO cinema(@PathVariable("id") Long cinemaId) {
    CinemaDTO cinemaDTO = cinemaService.findById(cinemaId);
    return cinemaDTO;
  }

  @PostMapping(value = RestContant.REST_ADD)
  public ResponseEntity<Object> createCinema(@RequestBody CinemaDTO model) {
    ResponseExceptionModel responseException = cinemaService.insert(model);
    return new ResponseEntity<>(responseException, responseException.getHttpMessage());
  }

  @PutMapping(value = RestContant.REST_UPDATE)
  public ResponseEntity<Object> updateCinema(@RequestBody CinemaDTO model) {
    ResponseExceptionModel responseException = cinemaService.update(model);
    return new ResponseEntity<>(responseException, responseException.getHttpMessage());
  }

  @DeleteMapping(value = RestContant.REST_DELETE_BY_ID)
  public ResponseEntity<Object> deleteCinema(@PathVariable(value = "id") Long cinemaId) {
    ResponseExceptionModel responseException = cinemaService.delete(cinemaId);
    return new ResponseEntity<>(responseException, responseException.getHttpMessage());
  }

}
