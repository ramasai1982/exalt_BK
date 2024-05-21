package com.exalt.producerconsumer.serviceTest;

import com.exalt.producerconsumer.model.Person;
import com.exalt.producerconsumer.service.SortVIPService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SortVIPServiceTest {

    private SortVIPService sortVIPService;

    @BeforeEach
    public void setUp() {
        sortVIPService = new SortVIPService();
    }

    @Test
    public void testSortVIPList() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("FirstName1", "Lastname1", true, new Date()));
        personList.add(new Person("FirstName2", "Lastname2", false, new Date()));
        personList.add(new Person("FirstName3", "Lastname3", true, new Date()));
        personList.add(new Person("FirstName4", "Lastname4", false, new Date()));

        List<Person> sortedList = sortVIPService.sortVIPList(personList);

        assertEquals(4, sortedList.size());
        assertTrue(sortedList.get(0).isStatusVIP());
        assertTrue(sortedList.get(1).isStatusVIP());
        assertFalse(sortedList.get(2).isStatusVIP());
        assertFalse(sortedList.get(3).isStatusVIP());
    }
}
