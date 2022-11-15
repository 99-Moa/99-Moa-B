package com.moa.moabackend.googleLogin;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/google")
public class GoogleController {
    private final GoogleService googleService;

    @GetMapping(value = "/login")
    public ResponseEntity<Object> moveGoogleInitUrl() {
        return googleService.moveGoogleInitUrl();
    }

    @GetMapping(value = "/login/redirect")
    public ResponseEntity<String> redirectGoogleLogin(@RequestParam(value = "code") String authCode) {
        return googleService.redirectGoogleLogin(authCode);
    }
}
