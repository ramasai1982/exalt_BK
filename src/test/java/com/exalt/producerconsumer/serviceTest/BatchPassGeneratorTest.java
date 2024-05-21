package com.exalt.producerconsumer.serviceTest;

import com.exalt.producerconsumer.model.Pass;
import com.exalt.producerconsumer.model.Person;
import com.exalt.producerconsumer.service.BatchPassGenerator;
import com.exalt.producerconsumer.service.PersonGenerator;
import com.exalt.producerconsumer.service.SortVIPService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class BatchPassGeneratorTest {

    @Mock
    private SortVIPService sortVIPService;

    @Mock
    private PersonGenerator personGenerator;

    @InjectMocks
    private BatchPassGenerator batchPassGenerator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        batchPassGenerator = new BatchPassGenerator(sortVIPService, personGenerator);
    }

    @Test
    public void testGeneratePassBatch() {
        int numberOfPasses = 5;
        List<Person> generatedPersons = new ArrayList<>();
        for (int i = 0; i < numberOfPasses; i++) {
            Person person = new Person("FirstName" + i, "LastName" + i, i % 2 == 0, new Date());
            generatedPersons.add(person);
        }

        when(personGenerator.generatePersonForPass(numberOfPasses)).thenReturn(generatedPersons);
        when(sortVIPService.sortVIPList(generatedPersons)).thenReturn(generatedPersons);

        List<Pass> passes = batchPassGenerator.generatePassBatch(numberOfPasses);

        assertEquals(numberOfPasses, passes.size());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dateTimeOfRequest = dateFormat.format(new Date());

        for (int i = 0; i < numberOfPasses; i++) {
            Pass pass = passes.get(i);
            Person person = generatedPersons.get(i);

            assertEquals(person.getFirstName(), pass.getFirstName());
            assertEquals(person.getLastName(), pass.getLastName());
            assertEquals(person.isStatusVIP(), pass.isStatusVIP());
            assertEquals(person.getDateOfBirth(), pass.getDateOfBirth());
            assertEquals(dateTimeOfRequest, pass.getDateTimeOfRequest());
            assertNotNull(pass.getDateTimeOfGeneration());
        }
    }

    @Test
    public void testDeleteLocallyCreatedBatchPasses() {
        int numberOfPasses = 5;
        List<Person> generatedPersons = new ArrayList<>();
        for (int i = 0; i < numberOfPasses; i++) {
            Person person = new Person("FirstName" + i, "LastName" + i, i % 2 == 0, new Date());
            generatedPersons.add(person);
        }

        when(personGenerator.generatePersonForPass(numberOfPasses)).thenReturn(generatedPersons);
        when(sortVIPService.sortVIPList(generatedPersons)).thenReturn(generatedPersons);

        batchPassGenerator.generatePassBatch(numberOfPasses);
        List<Pass> passes = batchPassGenerator.deleteLocallyCreatedBatchPasses();

        assertTrue(passes.isEmpty());
    }
}
