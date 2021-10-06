package ru.job4j.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UniqueStringGenerator {
    private final Set<String> strings = new HashSet<>();

    public String getUniqueString() {
        String string;
        do {
            string = RandomStringUtils.random(7, true, true);
        } while (strings.contains(string));
        strings.add(string);
        return string;
    }
}
