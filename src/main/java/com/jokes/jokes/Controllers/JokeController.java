package com.jokes.jokes.Controllers;

import com.jokes.jokes.Entities.Joke;
import com.jokes.jokes.Services.CategoryService;
import com.jokes.jokes.Services.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/")
public class JokeController {

    private static int currentPage = 1;
    private static int pageSize = 10;

    @Autowired
    JokeService jokeService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public String findJokes(Model model, @RequestParam("page") Optional<Integer> page){
        page.ifPresent(p -> currentPage = p);
        returnModelWithPaginatedSortedJokes(model);

        return "jokes";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newJokePage(Model model){
        model.addAttribute("jokeIncoming", new JokeIncoming());
        model.addAttribute("categories", categoryService.getAllCategories());

        return "newJokeForm";
    }

    @RequestMapping(value = "/addJoke", method = RequestMethod.POST)
    public String addJoke(Model model, @ModelAttribute JokeIncoming incomingJoke){
        try {
            jokeService.addJoke(new Joke(incomingJoke.getContent(), categoryService.getOneCategory(incomingJoke.getCategoryId()), 0, 0));
        } catch (Exception e) {
            model.addAttribute("error", "Sadržaj vica ne smije biti prazan!!!");
            model.addAttribute("jokeIncoming", new JokeIncoming());
            model.addAttribute("categories", categoryService.getAllCategories());
            return "newJokeForm";
        }
        returnModelWithPaginatedSortedJokes(model);

        return "jokes";
    }

    private void returnModelWithPaginatedSortedJokes(Model model) {
        Page<Joke> jokesPage = jokeService.getAllJokesSortedPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("jokesPage", jokesPage);
        model.addAttribute("currentPage", currentPage);

        int totalPages = jokesPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.PUT, params="action=like")
    public String like(Model model, @PathVariable("id") int id, @RequestParam("page") Optional<Integer> page) {
        page.ifPresent(p -> currentPage = p);

        Joke updateJoke = jokeService.getOneJoke(id);
        updateJoke.setLikes(updateJoke.getLikes() + 1);
        jokeService.updateJoke(updateJoke);

        returnModelWithPaginatedSortedJokes(model);

        return "jokes";
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.PUT, params="action=dislike")
    public String dislike(Model model, @PathVariable("id") int id, @RequestParam("page") Optional<Integer> page) {
        page.ifPresent(p -> currentPage = p);

        Joke updatedJoke = jokeService.getOneJoke(id);
        updatedJoke.setDislikes(updatedJoke.getDislikes() + 1);
        jokeService.updateJoke(updatedJoke);

        returnModelWithPaginatedSortedJokes(model);

        return "jokes";
    }
}
