package com.example.examenProjet1.controller;

import com.example.examenProjet1.model.Affirmation;
import com.example.examenProjet1.service.AffirmationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AffirmationController {

    private final AffirmationService affirmationService;

    public AffirmationController(AffirmationService affirmationService) {
        this.affirmationService = affirmationService;
    }

    @GetMapping("/affirmations")
    public List<Affirmation> getAllAffirmations() {
        return affirmationService.getAllAffirmations();
    }

    @GetMapping("/evaluate")
    public String evaluateAffirmation(@RequestParam String text) {
        return affirmationService.evaluateAffirmation(text);
    }
}
