package com.mercadolibre.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "stats")
public class Stats {
    
    @Id
    private String id;

    private long countMutantDna;
    private long countHumanDna;
    private double ratio;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCountMutantDna() {
        return countMutantDna;
    }

    public void setCountMutantDna(long countMutantDna) {
        this.countMutantDna = countMutantDna;
    }

    public long getCountHumanDna() {
        return countHumanDna;
    }

    public void setCountHumanDna(long countHumanDna) {
        this.countHumanDna = countHumanDna;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public double getRatio() {
        if(countHumanDna > 0){
            ratio = (double) countMutantDna / countHumanDna;
            return ratio;
        }
        return countMutantDna;
    }

}
