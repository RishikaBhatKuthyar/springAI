package com.rishika.springai_api.controller;

import com.rishika.springai_api.dto.bookDetails;
import com.rishika.springai_api.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
    @RequestMapping("/api/v1")
    public class AIController {

    @Autowired
    AIService aiService;

    @GetMapping("/joke")
    public String getJoke(@RequestParam String topic) {
        try {
            return aiService.getJoke(topic);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error generating joke: " + e.getMessage();
        }
    }

    @GetMapping("/books")
    public String getBooks(@RequestParam String category, @RequestParam String year) {
        try {
            return aiService.getBooks(category, year);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error generating Books: " + e.getMessage();
        }
    }

    @GetMapping("/books/json")
    public bookDetails getBooksInJSON(@RequestParam String category, @RequestParam String year) {
        try {
            return aiService.getBooksInJSON(category, year);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error generating Books", e);
        }

    }
}