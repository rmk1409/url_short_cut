package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.dao.SiteRepository;
import ru.job4j.model.WebSite;
import ru.job4j.model.WebSiteDto;

import java.util.Objects;

@Service
public class RegistrationService {
    private final UniqueStringGenerator uniqueStringGenerator;
    private final SiteRepository repository;

    public RegistrationService(UniqueStringGenerator uniqueStringGenerator, SiteRepository repository) {
        this.uniqueStringGenerator = uniqueStringGenerator;
        this.repository = repository;
    }

    public WebSiteDto create(WebSite webSite) {
        WebSite found = repository.findBySite(webSite.getSite());
        WebSiteDto dto = new WebSiteDto();
        if (Objects.nonNull(found)) {
            dto.setLogin(found.getLogin());
            dto.setPassword(found.getPassword());
        } else {
            dto.setRegistration(true);
            String login = uniqueStringGenerator.getUniqueString();
            String password = uniqueStringGenerator.getUniqueString();
            webSite.setLogin(login);
            webSite.setPassword(password);
            dto.setLogin(login);
            dto.setPassword(password);
            repository.save(webSite);
        }
        return dto;
    }
}
