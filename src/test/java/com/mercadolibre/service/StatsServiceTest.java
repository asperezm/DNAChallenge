package com.mercadolibre.service;

import com.mercadolibre.entity.Stats;
import com.mercadolibre.repository.StatsRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;



@SpringBootTest
public class StatsServiceTest {

    @Autowired
    private StatsRepository statsRepository;

    @Autowired
    private StatsService statsService;

    private Stats stats;

    @BeforeEach
    public void setUp() {
        statsRepository.deleteAll();
        stats = new Stats();
        stats.setId("1");
        stats.setCountMutantDna(10);
        stats.setCountHumanDna(5);
        stats.setRatio(2.0);
        statsRepository.save(stats);
    }

    @Test
    public void updateMutantTest() {
        //Act
        statsService.updateStats(true);

        //Assert
        Stats updatedStats = statsRepository.findById("1").get();
        assertEquals(11, updatedStats.getCountMutantDna());
    }

    @Test
    public void updateHumanTest() {
        //Act
        statsService.updateStats(false);

        //Assert
        Stats updatedStats = statsRepository.findById("1").get();
        assertEquals(6, updatedStats.getCountHumanDna());
    }

    @Test
    public void testCalculateRatioWithMutantAndHumanDna() {
        Stats stats = new Stats();
        stats.setCountMutantDna(40);
        stats.setCountHumanDna(100); 

        double expectedRatio = 40.0 / 100.0;
        
        assertEquals(expectedRatio, stats.getRatio(), "El ratio debería ser 0.4");
    }

    @Test
    public void testCalculateRatioWithZeroHumanDna() {
        Stats stats = new Stats();
        stats.setCountMutantDna(40);
        stats.setCountHumanDna(0);


        statsService.updateStats(true);

        double expectedRatio = 40.0;
        assertEquals(expectedRatio, stats.getRatio(), "El ratio debería ser 0.0 cuando no hay ADN humano");
    }
}