package com.exalt.producerconsumer.serviceTest;

import com.exalt.producerconsumer.model.Person;
import com.exalt.producerconsumer.service.DateOfBirthGenerator;
import com.exalt.producerconsumer.service.NameGenerator;
import com.exalt.producerconsumer.service.PersonGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class PersonGeneratorTest {

    @Mock
    private DateOfBirthGenerator dateOfBirthGenerator;

    @Mock
    private NameGenerator nameGenerator;

    @InjectMocks
    private PersonGenerator personGenerator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        personGenerator = new PersonGenerator(dateOfBirthGenerator, nameGenerator);
    }

    @Test
    public void testGeneratePersonForPass() {
        int numberOfPasses = 5;
        String mockName = "TestName";
        Date mockDateOfBirth = new Date();

        when(nameGenerator.nameGenerator()).thenReturn(mockName);
        when(dateOfBirthGenerator.dateOfBirth()).thenReturn(mockDateOfBirth);

        List<Person> personList = personGenerator.generatePersonForPass(numberOfPasses);

        assertEquals(numberOfPasses, personList.size());
        for (Person person : personList) {
            assertEquals(mockName, person.getFirstName());
            assertEquals(mockName, person.getLastName());
            assertEquals(mockDateOfBirth, person.getDateOfBirth());
        }

        verify(nameGenerator, times(numberOfPasses * 2)).nameGenerator();
        verify(dateOfBirthGenerator, times(numberOfPasses)).dateOfBirth();
    }
}
