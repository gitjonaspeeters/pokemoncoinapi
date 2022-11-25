package com.api.rest.Controller;

import com.api.rest.Model.Pokemoncoin;
import com.api.rest.Repo.CoinRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiControllers {

    @Autowired
    private CoinRepo coinRepo;

    @GetMapping("/")
    public String getPage() {
        return "Welcome to the pokemon coin page.<br><br>" +
                "To get a list of all coins, go to /coins <br>" +
                "To get a random coin, go to /randomCoin <br>" +
                "To get a coin by id, go to /coins/{id} <br>" +
                "To add a coin, go to /new <br>" +
                "To delete a coin, go to /delete/{id} <br>" +
                "To update a coin, go to /update/{id} <br>";
    }

    @GetMapping("/coins")
    public List<Pokemoncoin> getCoins() {
        return coinRepo.findAll();
    }

    @GetMapping("/randomCoin")
    public Pokemoncoin getRandomCoin() {
        List<Pokemoncoin> allCoins = coinRepo.findAll();
        int randomIndex = (int) (Math.random() * allCoins.size());
        return allCoins.get(randomIndex);
    }

    @GetMapping("/coins/{id}")
    public Pokemoncoin getCoinById(@PathVariable Long id) {
        return coinRepo.findById(id).get();
    }

    @PostMapping("/new")
    public Pokemoncoin addCoin(@RequestBody Pokemoncoin coin) {
        return coinRepo.save(coin);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCoin(@PathVariable Long id) {
        coinRepo.deleteById(id);
        return "Coin deleted";
    }

    @PutMapping("/update/{id}")
    public Pokemoncoin updateCoin(@PathVariable Long id, @RequestBody Pokemoncoin coin) {
        Pokemoncoin existingCoin = coinRepo.findById(id).get();
        existingCoin.setName(coin.getName());
        existingCoin.setColor(coin.getColor());
        existingCoin.setPack(coin.getPack());
        existingCoin.setYear(coin.getYear());
        existingCoin.setRarity(coin.getRarity());
        return coinRepo.save(existingCoin);
    }




}
