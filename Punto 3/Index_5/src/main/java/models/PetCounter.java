package models;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PetCounter {
    private List<Pet> pets;

    public PetCounter(List<Pet> pets) {
        this.pets = pets;
    }

    public Map<String, Long> countPetsByName() {
        return pets.stream()
                .collect(Collectors.groupingBy(Pet::getName, Collectors.counting()));
    }
}