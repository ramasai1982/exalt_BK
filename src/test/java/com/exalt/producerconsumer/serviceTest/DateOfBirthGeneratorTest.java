package com.exalt.producerconsumer.serviceTest;

import com.exalt.producerconsumer.service.DateOfBirthGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateOfBirthGeneratorTest {

    private DateOfBirthGenerator dateOfBirthGenerator;

    @BeforeEach
    public void setUp() {
        dateOfBirthGenerator = new DateOfBirthGenerator();
    }

    @Test
    public void testDateOfBirthNotNull() {
        Date dateOfBirth = dateOfBirthGenerator.dateOfBirth();
        assertNotNull(dateOfBirth, "Date of birth is not null");
    }

    @Test
    public void testDateOfBirthWithinRange() {
        long aDay = TimeUnit.DAYS.toMillis(1);
        long now = new Date().getTime();
        Date ninetyNineYearsAgo = new Date(now - aDay * 365 * 99);
        Date oneYearAgo = new Date(now - aDay * 365);

        Date dateOfBirth = dateOfBirthGenerator.dateOfBirth();

        assertTrue(dateOfBirth.after(ninetyNineYearsAgo) && dateOfBirth.before(oneYearAgo),
                "Date of birth within 99 years to 1 year");
    }

}
