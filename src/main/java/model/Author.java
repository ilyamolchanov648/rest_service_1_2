package model;

public class Author {
    private int id;
    private String name;

    // Конструкторы, геттеры и сеттеры

    // Конструкторы
    public Author() {
    }

    public Author(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Геттеры
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Сеттеры
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
