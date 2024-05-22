package com.exalt.producerconsumer.daoTest;


import com.exalt.producerconsumer.dao.PassDao;
import com.exalt.producerconsumer.model.Pass;
import com.exalt.producerconsumer.repo.PassRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PassDaoTest {

    @Mock
    private PassRepo passRepo;

    @InjectMocks
    private PassDao passDao;


    @Test
    void testSaveToDatabase() {

        Pass pass1 = Pass.builder()
                .firstName("FirstName1")
                .lastName("lastname1")
                .statusVIP(true)
                .dateOfBirth(new Date())
                .dateTimeOfRequest("2024-05-01T10:00:00")
                .dateTimeOfGeneration("2024-05-01T10:01:00")
                .build();

        Pass pass2 = Pass.builder()
                .firstName("FirstName2")
                .lastName("lastname2")
                .statusVIP(false)
                .dateOfBirth(new Date())
                .dateTimeOfRequest("2024-05-01T10:00:00")
                .dateTimeOfGeneration("2024-05-01T10:01:00")
                .build();

        List<Pass> passes = Arrays.asList(pass1, pass2);

        passDao.saveToDatabase(passes);

        verify(passRepo, times(1)).saveAll(passes);
    }

    @Test
    void testRetrieveAllPasses() {
        Pass pass1 = Pass.builder()
                .firstName("FirstName1")
                .lastName("lastname1")
                .statusVIP(true)
                .dateOfBirth(new Date())
                .dateTimeOfRequest("2024-05-01T10:00:00")
                .dateTimeOfGeneration("2024-05-01T10:01:00")
                .build();

        Pass pass2 = Pass.builder()
                .firstName("FirstName2")
                .lastName("lastname2")
                .statusVIP(false)
                .dateOfBirth(new Date())
                .dateTimeOfRequest("2024-05-01T10:00:00")
                .dateTimeOfGeneration("2024-05-01T10:01:00")
                .build();
        List<Pass> expectedPasses = Arrays.asList(pass1, pass2);

        when(passRepo.findAll()).thenReturn(expectedPasses);

        List<Pass> actualPasses = passDao.retrieveAllPasses();

        assertEquals(expectedPasses, actualPasses);
        verify(passRepo, times(1)).findAll();
    }

}
