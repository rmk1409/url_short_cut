package ru.job4j.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.dao.SiteRepository;
import ru.job4j.model.WebSite;
import ru.job4j.model.WebSiteDto;

import java.util.Objects;

import static java.util.Collections.emptyList;

@Service
public class WebSiteService implements UserDetailsService {
    private final UniqueStringGenerator uniqueStringGenerator;
    private final SiteRepository repository;
    private final PasswordEncoder encoder;

    public WebSiteService(UniqueStringGenerator uniqueStringGenerator, SiteRepository repository, PasswordEncoder encoder) {
        this.uniqueStringGenerator = uniqueStringGenerator;
        this.repository = repository;
        this.encoder = encoder;
    }

    public WebSiteDto create(WebSite webSite) {
        WebSite found = repository.findBySite(webSite.getSite());
        WebSiteDto dto = new WebSiteDto();
        if (Objects.isNull(found)) {
            dto.setRegistration(true);
            String login = uniqueStringGenerator.getUniqueString();
            String password = uniqueStringGenerator.getUniqueString();
            String encodedPassword = encoder.encode(password);
            webSite.setLogin(login);
            webSite.setPassword(encodedPassword);
            dto.setLogin(login);
            dto.setPassword(password);
            repository.save(webSite);
        }
        return dto;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        WebSite webSite = repository.findByLogin(login);
        if (webSite == null) {
            throw new UsernameNotFoundException(login);
        }
        return new User(webSite.getLogin(), webSite.getPassword(), emptyList());
    }
}
