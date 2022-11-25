package com.api.rest.Repo;


import com.api.rest.Model.Pokemoncoin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepo extends JpaRepository<Pokemoncoin, Long> {
}
