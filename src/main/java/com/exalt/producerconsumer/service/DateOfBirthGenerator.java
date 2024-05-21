package com.exalt.producerconsumer.service;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


@Service
public class DateOfBirthGenerator {

    // Method to generate random date between 99 years ago and 1 year
    public Date dateOfBirth() {
        long aDay = TimeUnit.DAYS.toMillis(1);
        long now = new Date().getTime();
        Date ninetyNineYearsAgo = new Date(now - aDay * 365 * 99);
        Date oneYearAgo = new Date(now - aDay * 365);

        long startMillis = ninetyNineYearsAgo.getTime();
        long endMillis = oneYearAgo.getTime();
        long randomMillisSinceEpoch = ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);
        return new Date(randomMillisSinceEpoch);
    }
}

