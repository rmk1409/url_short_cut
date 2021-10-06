package ru.job4j.dao;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.model.Link;

public interface LinkRepository extends CrudRepository<Link, Long> {
    Link findByUrl(String url);

    Link findByShortUrl(String shortUrl);
}
