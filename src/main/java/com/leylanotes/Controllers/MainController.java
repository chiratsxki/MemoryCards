package com.leylanotes.Controllers;

import com.leylanotes.models.Cards;
import com.leylanotes.repo.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private CardsRepository cardsRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Title");
        return "home";
    }

    @GetMapping("/addCard")
    public String addCard(Model model) {
        return "addCard";
    }


    @GetMapping("/allCards")
    public String showAllCards(Model model) {
        Iterable<Cards> cardsIterable = cardsRepository.findAll();
        model.addAttribute("cards", cardsIterable);
        return "allCards";
    }


    @PostMapping("/addCard")
    public String cardPostAdd(@RequestParam String frontSide, @RequestParam String backSide, Model model) {
        Cards  card = new Cards(frontSide, backSide);
        cardsRepository.save(card);
        return "addCard";
    }

    @GetMapping("/allCards/{id}")
    public String cardDetails(@PathVariable(value = "id") long id, Model model) {

        if (!cardsRepository.existsById(id)) {
            return "home";
        }
        Optional<Cards> card = cardsRepository.findById(id);
        ArrayList<Cards> res = new ArrayList<>();
        card.ifPresent(res::add);
        model.addAttribute("post", res);
        return "cardDetails";
    }

    @GetMapping("/card/{id}/edit")
    public String cardEdit(@PathVariable(value = "id") long id, Model model) {

        if (!cardsRepository.existsById(id)) {
            return "/home";
        }
        Optional<Cards> card = cardsRepository.findById(id);
        ArrayList<Cards> res = new ArrayList<>();
        card.ifPresent(res::add);
        model.addAttribute("post", res);
        return "cardEdit";
    }

    @PostMapping("/card/{id}/edit")
    public String cardPostUpdate(@PathVariable(value = "id") long id,@RequestParam String frontSide, @RequestParam String backSide, Model model) {
        Cards  card = cardsRepository.findById(id).orElseThrow();
        card.setFrontSide(frontSide);
        card.setBackSide(backSide);
        cardsRepository.save(card);
        return "addCard";
    }

    @PostMapping("/card/{id}/remove")
    public String cardPostDelete(@PathVariable(value = "id") long id, Model model) {
        Cards  card = cardsRepository.findById(id).orElseThrow();
        cardsRepository.delete(card);

        return "allCards";
    }

    @GetMapping("/learning")
    public String learnAllCards(Model model) {
        Iterable<Cards> cardsIterable = cardsRepository.findAll();
        model.addAttribute("cards", cardsIterable);
        return "learning";
    }


}
