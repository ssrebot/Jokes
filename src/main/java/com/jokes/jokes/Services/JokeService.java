package com.jokes.jokes.Services;

import com.jokes.jokes.Entities.Joke;
import com.jokes.jokes.Repositories.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.jokes.jokes.Entities.Joke.JokeComparator;

@Service
public class JokeService {

    @Autowired
    private JokeRepository jokeRepository;

    public Page<Joke> getAllJokesSortedPaginated(Pageable pageable){

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Joke> list;
        List<Joke> sortedJokes = getAllJokesSorted();

        if (sortedJokes.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, sortedJokes.size());
            list = sortedJokes.subList(startItem, toIndex);
        }

        Page<Joke> jokesPage
                = new PageImpl<Joke>(list, PageRequest.of(currentPage, pageSize), sortedJokes.size());

        return jokesPage;
    }

    public List<Joke> getAllJokesSorted(){

        List<Joke> jokes = (List<Joke>) jokeRepository.findAll();

        jokes.sort(JokeComparator);

        return jokes;
    }

    public Joke getOneJoke(int id){

        Joke joke = jokeRepository.findById(id).get();

        return joke;
    }

    public void updateJoke(Joke joke){
        jokeRepository.save(joke);
    }

    public void addJoke(Joke joke) throws Exception {
        if(joke.getContent().isEmpty() || joke.getCategory() == null)
            throw new Exception();
        jokeRepository.save(joke);
    }
}
