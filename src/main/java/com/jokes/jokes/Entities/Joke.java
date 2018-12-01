package com.jokes.jokes.Entities;

import javax.persistence.*;
import java.util.Comparator;

@Entity
@Table(name = "joke")
public class Joke {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="my_sequence")
    @SequenceGenerator(name="my_sequence", sequenceName="my_sequence", allocationSize=1)
    private int id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @CollectionTable(name="category", joinColumns= {@JoinColumn(name="category_id")})
    private Category category;

    private int likes;

    private int dislikes;

    public Joke() {
    }

    public Joke(String content, Category category, int likes, int dislikes) {
        this.content = content;
        this.category = category;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public static Comparator<Joke> JokeComparator = (o1, o2) -> {
        int Likes1 = o1.getLikes() - o1.getDislikes();
        int Likes2 = o2.getLikes() - o2.getDislikes();

        return ((Integer)Likes2).compareTo(Likes1);
    };
}
