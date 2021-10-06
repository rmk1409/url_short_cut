package ru.job4j.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.service.LinkService;

@RestController
@RequestMapping("/statistic")
public class StatsController {
    private final LinkService service;

    public StatsController(LinkService service) {
        this.service = service;
    }

    @GetMapping("/")
    public Object[] showStats() {
        return service.getStats().toArray();
    }
}
