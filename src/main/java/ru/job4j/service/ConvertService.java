package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.dao.LinkRepository;
import ru.job4j.model.Link;

import java.util.Objects;

@Service
public class ConvertService {
    private final LinkRepository repository;
    private final UniqueStringGenerator uniqueStringGenerator;

    public ConvertService(LinkRepository repository, UniqueStringGenerator uniqueStringGenerator) {
        this.repository = repository;
        this.uniqueStringGenerator = uniqueStringGenerator;
    }

    public String convert(Link link) {
        Link found = repository.findByUrl(link.getUrl());
        String shortUrl;
        if (Objects.nonNull(found)) {
            shortUrl = found.getShortUrl();
        } else {
            shortUrl = uniqueStringGenerator.getUniqueString();
            link.setShortUrl(shortUrl);
            repository.save(link);
        }
        return shortUrl;
    }
}
