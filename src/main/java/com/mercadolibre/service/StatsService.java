package com.mercadolibre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.entity.Stats;
import com.mercadolibre.repository.StatsRepository;

@Service
public class StatsService {
    
    @Autowired
    private StatsRepository statsRepository;

    private Stats getStatsDocument() {
        return statsRepository.findById("1").orElseGet(() -> {
            Stats newStats = new Stats();
            newStats.setId("1");
            newStats.setCountMutantDna(0);
            newStats.setCountHumanDna(0);
            newStats.setRatio(0.0);
            return newStats;
        });
    }

    public void updateStats(boolean isMutant) {
        Stats stats = getStatsDocument();
        
        if (isMutant) {
            stats.setCountMutantDna(stats.getCountMutantDna() + 1);
        } else {
            stats.setCountHumanDna(stats.getCountHumanDna() + 1);
        }

        long countMutantDna = stats.getCountMutantDna();
        long countHumanDna = stats.getCountHumanDna();
        double ratio = (countHumanDna == 0) ? countMutantDna : (double) countMutantDna / countHumanDna;
        stats.setRatio(ratio);

        statsRepository.save(stats);
    }

    public Stats getStats() {
        return getStatsDocument();
    }
}
