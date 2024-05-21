package com.exalt.producerconsumer.service;

import com.exalt.producerconsumer.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class SortVIPService {
    public List<Person> sortVIPList(List<Person> personListToBeSorted){

        List<Person> personListSorted;
        List<Person> personListNoVIP;

        Predicate<Person> isNotVIP = personList1 -> !personList1.isStatusVIP();
        Predicate<Person> isVIP = Person::isStatusVIP;

        //passListToBeGenerated contains only the list of VIPs
        personListSorted = personListToBeSorted.stream()
                .filter(isVIP)
                .collect(Collectors.toList());

        //passListNoVIPToBeGenerated contains list of non VIPs
        personListNoVIP = personListToBeSorted.stream()
                .filter(isNotVIP)
                .toList();

        //passListToBeGenerated now contains VIPs list on the top and non VIPs on the bottom
        personListSorted.addAll(personListNoVIP);

        return personListSorted;
    }
}
