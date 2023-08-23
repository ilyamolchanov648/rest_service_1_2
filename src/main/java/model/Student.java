package model;

public class Student {
    private int id;
    private String name;

    // Конструкторы, геттеры и сеттеры

    // Конструкторы
    public Student() {
    }

    public Student(int id, String name) {
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
