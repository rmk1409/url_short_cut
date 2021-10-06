package ru.job4j.dao;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.model.WebSite;

public interface SiteRepository extends CrudRepository<WebSite, Long> {
    WebSite findBySite(String site);
}
