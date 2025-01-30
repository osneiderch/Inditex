package models;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Pet {
    private long id;
    private String name;

    public Pet(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Map<String, Long> countPetsByName(List<Pet> pets) {
        return pets.stream().collect(Collectors.groupingBy(Pet::getName, Collectors.counting()));
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}