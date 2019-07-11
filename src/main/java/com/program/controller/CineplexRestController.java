package com.program.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.program.conmmon.RestContant;
import com.program.dto.CineplexDTO;
import com.program.service.CineplexService;

@RestController
@RequestMapping("/rest/api/cineplex")
public class CineplexRestController {

  @Autowired
  private CineplexService cineplexService;

  @GetMapping(value = RestContant.REST_ALL)
  public @ResponseBody List<CineplexDTO> cineplexs() {
    List<CineplexDTO> cineplexDTOs = cineplexService.findAll();
    return cineplexDTOs;
  }

  @GetMapping(value = RestContant.REST_BY_ID)
  public @ResponseBody CineplexDTO cineplex(@PathVariable("id") Long cineplexId) {
    CineplexDTO cineplexDTO = cineplexService.findById(cineplexId);
    return cineplexDTO;
  }

  @PostMapping(value = RestContant.REST_ADD)
  public ResponseEntity<Object> createCineplex(@RequestBody CineplexDTO model) {
    boolean status = cineplexService.insert(model);
    if (status == false) {
      return ResponseEntity.notFound().build();
    }
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(RestContant.REST_BY_ID)
        .buildAndExpand(model.getCineplexId()).toUri();
    return ResponseEntity.created(location).build();
  }

  @PutMapping(value = RestContant.REST_UPDATE)
  public ResponseEntity<Object> updateCineplex(@RequestBody CineplexDTO model) {
    CineplexDTO cineplexOptional = cineplexService.findById(model.getCineplexId());
    if (cineplexOptional == null) {
      return ResponseEntity.notFound().build();
    }
    cineplexService.update(model);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(value = RestContant.REST_DELETE_BY_ID)
  public Map<String, Boolean> deleteCinePlex(@PathVariable(value = "id") Long cineplexId) {
    Map<String, Boolean> response = new HashMap<String, Boolean>();
    boolean isDeleteStatus = cineplexService.delete(cineplexId);

    if (isDeleteStatus == false) {
      response.put("undeleted", isDeleteStatus);
      return response;
    }
    response.put("deleted", isDeleteStatus);
    return response;
  }
}