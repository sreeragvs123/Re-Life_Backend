package com.example.Relife_backend.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * TEST CONTROLLER - Use this to verify JSON serialization
 * Access:
 *
 * DELETE THIS FILE after debugging!
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/json")
    public Map<String, Object> testJsonSerialization() {
        Map<String, Object> response = new HashMap<>();

        // Test Long serialization
        response.put("longValue", 12345L);
        response.put("intValue", 100);
        response.put("stringValue", "test");
        response.put("booleanValue", true);

        return response;
    }
}

/**
 * EXPECTED OUTPUT (numbers should NOT have quotes):
 * {
 *   "longValue": 12345,      ← No quotes (correct)
 *   "intValue": 100,         ← No quotes (correct)
 *   "stringValue": "test",   ← Has quotes (correct for strings)
 *   "booleanValue": true     ← No quotes (correct)
 * }
 *
 * WRONG OUTPUT (if numbers have quotes):
 * {
 *   "longValue": "12345",    ← Has quotes (WRONG!)
 *   "intValue": "100",       ← Has quotes (WRONG!)
 *   "stringValue": "test",
 *   "booleanValue": "true"   ← Has quotes (WRONG!)
 * }
 */