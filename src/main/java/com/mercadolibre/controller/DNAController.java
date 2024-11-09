package com.mercadolibre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.service.DNAService;
import com.mercadolibre.service.StatsService;

@RestController
@RequestMapping("/api/dna")
public class DNAController {
    
    @Autowired
    private DNAService dnaService;

    @Autowired
    private StatsService statsService;

    @PostMapping("/mutant")
    public ResponseEntity<String> DNAMutant(@RequestBody String[] dna){

        boolean isMutant = dnaService.isMutant(dna);
        statsService.updateStats(isMutant);

        if(isMutant){
            return ResponseEntity.status(HttpStatus.OK).body("Is a mutant");
        }
        else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Is not a mutant"); 
        }
    }

    @GetMapping("/stats")
    public Object getStats() {
        
        return statsService.getStats();
    }
}
