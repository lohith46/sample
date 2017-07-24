package com.lohith.project.controller;

import com.lohith.project.Repository.HotelRepository;
import com.lohith.project.exception.ResourceNotFoundException;
import com.lohith.project.models.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lohith.km on 21-07-2017.
 */
@RestController
@RequestMapping("/hotels")
public class ApiController {

    @Autowired
    HotelRepository hotelRepository;

    @RequestMapping(value = "/createHotel",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> createHotel(@RequestBody Hotel hotel) {
        Hotel hotelObj = hotelRepository.findOne(hotel.getId());
        if(!StringUtils.isEmpty(hotelObj)){
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("message", "Hotel already exists");
            dataMap.put("hotel", hotel);
            return dataMap;
        }
        hotel = hotelRepository.save(hotel);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("message", "Hotel Added successfully");
        dataMap.put("status", "1");
        dataMap.put("hotel", hotel);
        return dataMap;
    }

    /**
     * GET /read  --> Read a Hotel by hotel id from the database.
     */
    @RequestMapping(value = "/getHotel/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getHotel(@PathVariable("id") Long id) {
        Hotel hotel = hotelRepository.findOne(id);
        checkResourceFound(hotel);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("message", "Hotel is found successfully");
        dataMap.put("status", "1");
        dataMap.put("hotel", hotel);
        return dataMap;
    }


    @RequestMapping(value = "/checkHotelExists/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> checkHotelExists(@PathVariable("id") Long id) {
        Hotel hotel = hotelRepository.findOne(id);
        checkResourceFound(hotel);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("message", "Hotel is found successfully");
        dataMap.put("status", "1");
        dataMap.put("hotel", hotel);
        return dataMap;
    }

    @RequestMapping(value = "/deleteHotels",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> deleteHotels() {
        hotelRepository.deleteAll();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("message", "Hotels are deleted successfully");
        dataMap.put("status", "1");
        return dataMap;
    }

    public static <T> T checkResourceFound(final T resource) {
        if (resource == null) {
            throw new ResourceNotFoundException("resource not found");
        }
        return resource;
    }
}
