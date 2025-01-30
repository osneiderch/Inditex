package models;

public class Pet {
    private long id;
    private String name;

    public Pet() {
        // Constructor vacío para Jackson
    }

    public Pet(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}