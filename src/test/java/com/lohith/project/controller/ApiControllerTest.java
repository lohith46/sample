package com.lohith.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lohith.project.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by lohith.km on 21-07-2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();
    private Map<String, Object> createHotelRequestMap;

    @Before
    public void setUp() throws Exception {
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> deleteEntity = new HttpEntity<String>(null, headers);
        createHotelRequestMap = buildRequest("/CreateHotelAPIRequest.json");

        ResponseEntity<String> deleteResponse = restTemplate.exchange(
                createURLWithPort("/hotels/deleteHotels"),
                HttpMethod.DELETE, deleteEntity, String.class);
    }

    @Test
    public void testToDeleteSaveAndGetHotelBasedOnTheRequestInput() {
        
        HttpEntity<Map> entity = new HttpEntity<Map>(createHotelRequestMap, headers);

        // To save hotel in the database
        ResponseEntity<String> savedResponse = restTemplate.exchange(
                createURLWithPort("/hotels/createHotel"),
                HttpMethod.POST, entity, String.class);

        String expectedSaveDataResponse = "{\"hotel\":{\"id\":10,\"name\":\"Adigas\",\"description\":\"Coffee Shop\",\"city\":\"Bangalore\",\"rating\":10},\"message\":\"Hotel Added successfully\",\"status\":\"1\"}";

        JSONAssert.assertEquals(expectedSaveDataResponse, savedResponse.getBody(), false);

        // To get hotel data in the database which is stored above
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/hotels/getHotel/10"),
                HttpMethod.GET, entity, String.class);

        String expected = "{\"hotel\":{\"id\":10,\"name\":\"Adigas\",\"description\":\"Coffee Shop\",\"city\":\"Bangalore\",\"rating\":10},\"message\":\"Hotel is found successfully\",\"status\":\"1\"}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private String createURLWithPort(String uri) {
        return "http://admin:secret@localhost:" + port + uri;
    }

    private Map<String, Object> buildRequest(String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = this.getClass().getResourceAsStream(fileName);
        try {
            return mapper.readValue(is, Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}