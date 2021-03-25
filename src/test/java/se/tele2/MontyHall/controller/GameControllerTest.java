package se.tele2.MontyHall.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameControllerTest {

    private static final String INDEX = "http://localhost:5000/";
    private final RestTemplate restTemplate = new RestTemplate();

    @InjectMocks
    GameController sut;

    @BeforeEach
    void setUp() {
    }

    @Test
    void should_show_welcome_page() throws Exception {

        URI uri = new URI(INDEX);

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    void should_show_the_result() throws Exception {

        final String url = "http://localhost:5000/result" + "?times=10";

        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);

        assertEquals(200, result.getStatusCodeValue());

    }

}