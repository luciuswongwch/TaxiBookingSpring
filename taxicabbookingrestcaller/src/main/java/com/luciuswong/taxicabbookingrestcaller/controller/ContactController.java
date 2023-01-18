package com.luciuswong.taxicabbookingrestcaller.controller;

import com.luciuswong.taxicabbookingrestcaller.model.Contact;
import com.luciuswong.taxicabbookingrestcaller.model.Response;
import com.luciuswong.taxicabbookingrestcaller.proxy.ContactProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class ContactController {
    // for using OpenFeign
    @Autowired
    ContactProxy contactProxy;

    // for using RestTemplate
    @Autowired
    RestTemplate restTemplate;

    // for using WebClient
    @Autowired
    WebClient webClient;


    // get messages using OpenFeign
    @GetMapping("/getMessages")
    public List<Contact> getMessages(@RequestParam("status") String status) {
        return contactProxy.getMessagesByStatus(status);
    }

    // save message using RestTemplate
    @PostMapping("/saveMessageRestTemplate")
    public ResponseEntity<Response> saveMessageRestTemplate(@RequestBody Contact contact) {
        String uri = "http://localhost:8080/api/contact/saveContactMessage";
        HttpHeaders headers = new HttpHeaders();
        headers.add("invocationFrom", "RestTemplate");
        HttpEntity<Contact> httpEntity = new HttpEntity<>(contact, headers);
        ResponseEntity<Response> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, Response.class);
        return responseEntity;
    }

    // save message using WebClient
    @PostMapping("/saveMessageWebClient")
    public Mono<Response> saveMessageWebClient(@RequestBody Contact contact) {
        String uri = "http://localhost:8080/api/contact/saveContactMessage";
        return webClient.post().uri(uri)
                .header("invocationFrom", "WebClient")
                .body(Mono.just(contact), Contact.class)
                .retrieve()
                .bodyToMono(Response.class);
    }
}
