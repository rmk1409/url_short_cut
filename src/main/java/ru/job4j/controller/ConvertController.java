package ru.job4j.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.model.Link;
import ru.job4j.service.LinkService;

import java.util.Map;

@RestController
@RequestMapping("/convert")
public class ConvertController {
    private final LinkService service;

    public ConvertController(LinkService service) {
        this.service = service;
    }

    @PostMapping("/")
    public Map<String, Object> convert(@RequestBody Link link) {
        return Map.of("code", service.convert(link));
    }
}
