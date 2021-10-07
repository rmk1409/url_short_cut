package ru.job4j.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.model.Link;

public interface LinkRepository extends CrudRepository<Link, Long> {
    Link findByUrl(String url);

    Link findByShortUrl(String shortUrl);

    @Modifying
    @Transactional
    @Query("update Link set invocationQuantity = invocationQuantity + 1 where id = ?1")
    void incrementInvocationQuantity(Long id);
}
