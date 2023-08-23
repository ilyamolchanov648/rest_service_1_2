package model;

public class Course {
    private int id;
    private String title;
    private int authorId; // Ссылка на автора

    // Конструкторы, геттеры и сеттеры

    // Конструкторы
    public Course() {
    }

    public Course(int id, String title, int authorId) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
    }

    // Геттеры
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getAuthorId() {
        return authorId;
    }

    // Сеттеры
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

}

