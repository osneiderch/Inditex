package tests;

import com.intuit.karate.junit5.Karate;

class PetStoreTest {

    @Karate.Test
    Karate testAll() {
        return Karate.run("src/test/java/features/PetStore.feature");
    }
}

