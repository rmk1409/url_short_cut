package ru.job4j.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.model.WebSite;
import ru.job4j.model.WebSiteDto;
import ru.job4j.service.RegistrationService;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    private final RegistrationService service;

    public RegistrationController(RegistrationService service) {
        this.service = service;
    }

    @PostMapping("/")
    public WebSiteDto register(@RequestBody WebSite webSite) {
        return service.create(webSite);
    }
}
