package com.exalt.producerconsumer.serviceTest;

import com.exalt.producerconsumer.service.NameGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NameGeneratorTest {
    private NameGenerator nameGenerator;

    @BeforeEach
    public void setUp() {
        nameGenerator = new NameGenerator();
    }

    @Test
    public void testNameGeneratorNotNull() {
        String name = nameGenerator.nameGenerator();
        assertNotNull(name, "Generated name should not be null");
    }

    @RepeatedTest(100)
    public void testNameGeneratorLength() {
        String name = nameGenerator.nameGenerator();
        assertTrue(name.length() >= 4 && name.length() <= 6, "Generated name length should be between 4 and 6");
    }

    @RepeatedTest(100)
    public void testNameGeneratorFirstLetterCapitalized() {
        String name = nameGenerator.nameGenerator();
        assertTrue(Character.isUpperCase(name.charAt(0)), "First letter of the generated name should be capitalized");
    }

    @RepeatedTest(100)
    public void testNameGeneratorOnlyLetters() {
        String name = nameGenerator.nameGenerator();
        assertTrue(name.chars().allMatch(Character::isLetter), "Generated name should only contain letters");
    }
}
