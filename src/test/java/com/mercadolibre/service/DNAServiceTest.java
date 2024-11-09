package com.mercadolibre.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DNAServiceTest {

    private final DNAService dnaService = new DNAService();

    @Test
    void isMutantTest() {
        String[] mutantDna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };

        boolean result = dnaService.isMutant(mutantDna);
        assertTrue(result, "DNA es mutante");
    }

    @Test
    void isNotMutantTest() {
        String[] nonMutantDna = {
            "ATGCAT",   
            "CAGTGA",   
            "TTATGT",   
            "AGAGAG",   
            "CCTGAA",   
            "TCACTG"
        };

        boolean result = dnaService.isMutant(nonMutantDna);
        assertFalse(result, "DNA no es mutante");
    }
}