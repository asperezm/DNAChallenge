package com.mercadolibre.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StatsTest {

    @Test
    public void setAndGetIdTest() {
        Stats stats = new Stats();
        stats.setId("123");
        assertEquals("123", stats.getId());
    }

    @Test
    public void SetAndGetCountMutantDnaTest() {
        Stats stats = new Stats();
        stats.setCountMutantDna(10);
        assertEquals(10, stats.getCountMutantDna());
    }

    @Test
    public void setAndGetCountHumanDnaTest() {
        Stats stats = new Stats();
        stats.setCountHumanDna(20);
        assertEquals(20, stats.getCountHumanDna());
    }

    @Test
    public void setAndGetRatioTest() {
        Stats stats = new Stats();
        stats.setCountMutantDna(10);
        stats.setCountHumanDna(20);
        stats.setRatio(0.5);
        assertEquals(0.5, stats.getRatio());
    }
}