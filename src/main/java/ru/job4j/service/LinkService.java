package ru.job4j.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.dao.LinkRepository;
import ru.job4j.model.Link;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class LinkService {
    private final LinkRepository repository;
    private final UniqueStringGenerator uniqueStringGenerator;

    public LinkService(LinkRepository repository, UniqueStringGenerator uniqueStringGenerator) {
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

    @Transactional
    public String getFullUrl(String code) {
        Link link = repository.findByShortUrl(code);
        String redirectPath = "";
        if (Objects.nonNull(link)) {
            redirectPath = link.getUrl();
            repository.incrementInvocationQuantity(link.getId());
            repository.save(link);
        }
        return redirectPath;
    }

    public List<Map<String, Object>> getStats() {
        Iterable<Link> all = repository.findAll();
        List<Map<String, Object>> stats = new ArrayList<>();
        all.forEach(link -> stats.add(Map.of("url", link.getUrl(), "total", link.getInvocationQuantity())));
        return stats;
    }
}
