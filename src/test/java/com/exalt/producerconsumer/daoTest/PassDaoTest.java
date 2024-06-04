package com.exalt.producerconsumer.daoTest;

import com.exalt.producerconsumer.dao.PassDao;
import com.exalt.producerconsumer.exception.UserNotFoundException;
import com.exalt.producerconsumer.model.Pass;
import com.exalt.producerconsumer.repo.PassRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PassDaoTest {

    @Mock
    private PassRepo passRepo;

    @InjectMocks
    private PassDao passDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveToDatabase() {
        List<Pass> passes = new ArrayList<>();
        passes.add(new Pass(1, "Rama1", "Pannir1", true, new Date(), "2024-06-01 00:00:00", "2024-06-02 00:00:00"));
        passDao.saveToDatabase(passes);

        verify(passRepo, times(1)).saveAll(passes);
    }

    @Test
    public void testSave() {
        Pass pass = new Pass(1, "Rama1", "Pannir1", true, new Date(), "2024-06-01 00:00:00", "2024-06-02 00:00:00");

        when(passRepo.save(any(Pass.class))).thenReturn(pass);

        Pass savedPass = passDao.save(pass);

        assertEquals(pass, savedPass);
    }

    @Test
    public void testFindById() {
        Pass pass = new Pass(1, "Rama1", "Pannir1", true, new Date(), "2024-06-01 00:00:00", "2024-06-02 00:00:00");

        when(passRepo.findById(1)).thenReturn(Optional.of(pass));

        Pass foundPass = passDao.findById(1);

        verify(passRepo, times(1)).findById(1);
        assertEquals(pass, foundPass);
    }

    @Test
    public void testFindById_UserNotFound() {
        when(passRepo.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            passDao.findById(1);
        });

        String expectedMessage = "User id 1 was not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testDeleteById() {
        Pass pass = new Pass(1, "Rama1", "Pannir1", true, new Date(), "2024-06-01 00:00:00", "2024-06-02 00:00:00");

        when(passRepo.findById(1)).thenReturn(Optional.of(pass));
        doNothing().when(passRepo).delete(pass);

        passDao.deleteById(1);

        verify(passRepo, times(1)).delete(pass);
    }

    @Test
    public void testRetrieveAllPasses() {
        List<Pass> passes = new ArrayList<>();
        passes.add(new Pass(1, "Rama1", "Pannir1", true, new Date(), "2024-06-01 00:00:00", "2024-06-02 00:00:00"));

        when(passRepo.findAll()).thenReturn(passes);

        List<Pass> retrievedPasses = passDao.retrieveAllPasses();

        verify(passRepo, times(1)).findAll();
        assertEquals(passes, retrievedPasses);
    }
}
