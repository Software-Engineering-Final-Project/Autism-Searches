package com.articlefetch.app.Controller.ResponseEntities;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class SuccessResponseEntity {

    public static ResponseEntity<Map<String, String>> createdResponseEntity() {
        Map<String, String> body = new HashMap<>();
        body.put("message", "Success");
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    public static ResponseEntity<Map<String, String>> updatedResposeEntity() {
        Map<String, String> body = new HashMap<>();
        body.put("message", "Success");
        return new ResponseEntity<>(body, HttpStatus.ACCEPTED);
    }
}