package com.mercadolibre.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mercadolibre.entity.Stats;
import com.mercadolibre.service.DNAService;
import com.mercadolibre.service.StatsService;



@SpringBootTest
public class DNAControllerTest {

    @MockBean
    private DNAService dnaService;

    @MockBean
    private StatsService statsService;

    @Autowired
    private DNAController dnaController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(dnaController).build();
    }

    @Test
    public void DNAMutantTest() throws Exception {
        String[] dna = {"ATGC", "CATA", "GGGC", "TTAG"};
        when(dnaService.isMutant(dna)).thenReturn(true);

        mockMvc.perform(post("/api/dna/mutant")
                .contentType("application/json")
                .content("[\"ATGC\", \"CATA\", \"GGGC\", \"TTAG\"]"))
                .andExpect(status().isOk())
                .andExpect(content().string("Is a mutant"));

        verify(dnaService, times(1)).isMutant(dna);
        verify(statsService, times(1)).updateStats(true);
    }

    @Test
    public void DNANonMutantTest() throws Exception {
        String[] dna = {"ATGC", "CATA", "GGGC", "TTAG"};
        when(dnaService.isMutant(dna)).thenReturn(false);

        mockMvc.perform(post("/api/dna/mutant")
                .contentType("application/json")
                .content("[\"ATGC\", \"CATA\", \"GGGC\", \"TTAG\"]"))
                .andExpect(status().isForbidden())
                .andExpect(content().string("Is not a mutant"));

        verify(dnaService, times(1)).isMutant(dna);
        verify(statsService, times(1)).updateStats(false);
    }

    @Test
    public void getStatsTest() throws Exception {
        Stats stats = new Stats();
        stats.setCountMutantDna(40);
        stats.setCountHumanDna(100);
        stats.setRatio(0.4);

        when(statsService.getStats()).thenReturn(stats);

        mockMvc.perform(get("/api/dna/stats")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Esperamos que la respuesta sea 200 OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Esperamos tipo de contenido JSON
                .andExpect(jsonPath("$.countMutantDna").value(40)) // Validamos que el valor countMutantDna es 40
                .andExpect(jsonPath("$.countHumanDna").value(100)) // Validamos que el valor countHumanDna es 100
                .andExpect(jsonPath("$.ratio").value(0.4)); // Validamos que el valor ratio es 0.4

        verify(statsService, times(1)).getStats();
    }
}