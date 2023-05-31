package com.leylanotes.repo;

import com.leylanotes.models.Cards;
import org.springframework.data.repository.CrudRepository;

public interface CardsRepository extends CrudRepository<Cards, Long> {
}
