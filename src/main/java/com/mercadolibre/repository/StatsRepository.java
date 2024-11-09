package com.mercadolibre.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mercadolibre.entity.Stats;

@Repository
public interface StatsRepository extends MongoRepository<Stats, String>{

}
