package com.jokes.jokes.Controllers;

public class JokeIncoming {

    private String content;
    private int categoryId;

    public JokeIncoming(String content, int categoryId) {
        this.content = content;
        this.categoryId = categoryId;
    }

    public JokeIncoming() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
