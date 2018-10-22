package com.example.demo;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RestControllerTest {

    @GetMapping(value = "/code", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getCode(@RequestParam String code, HttpServletRequest request) {

        System.out.println("Hiiiiiii");
         RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        //UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://login.microsoftonline.com/9ea448ab-4674-4013-b08d-bbeb0ec93214/oauth2/token")

        MultiValueMap<String, String> queryParam = new LinkedMultiValueMap<>();
        queryParam.add("client_id", "5be97854-2104-42f8-96ae-58049fba429e");
                queryParam.add("grant_type", "authorization_code");
                queryParam.add("redirect_uri", "http://localhost:8080/code");
                queryParam.add("client_secret", "A3LgSIwlgzrU+9fZnyL/WdPrqiNMDD3bLN0J9tkF7yg=");
                queryParam.add("code", code);


        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(queryParam, headers);
        ResponseEntity responseEntity = restTemplate.exchange("https://login.microsoftonline.com/a187e4e4-cb9c-4989-8d93-2a2c1bb235c6/oauth2/token", HttpMethod.POST, entity, String.class);

        return responseEntity;
    }

    @GetMapping("/authResource")
    public ResponseEntity<String> getMessage() {
        return new ResponseEntity<>("You are accessing protected Resource", HttpStatus.OK);
    }
}
