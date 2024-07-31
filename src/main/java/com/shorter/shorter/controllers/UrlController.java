package com.shorter.shorter.controllers;

import com.shorter.shorter.dtos.UrlDto;
import com.shorter.shorter.entities.UrlEntity;
import com.shorter.shorter.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("/api/v1")
public class UrlController {

    @Autowired
    UrlService service;

    @RequestMapping(path = "/url/{id}", method = RequestMethod.GET)
    public UrlEntity findById(@PathVariable UUID id) {
        Optional<UrlEntity> url = this.service.findById(id);
        if(url.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return url.get();
    }

    @RequestMapping(path = "/short/{shortUrl}", method = RequestMethod.GET)
    public UrlEntity findByShortUrl(@PathVariable String shortUrl) {
        Optional<UrlEntity> url = this.service.findByShortUrl(shortUrl);
        if(url.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return url.get();
    }

    @RequestMapping(path = "/default", method = RequestMethod.POST)
    public UrlEntity findByDefaultUrl(@RequestBody UrlDto body) {
        Optional<UrlEntity> url = this.service.findByDefaultUrl(body.defaultUrl());
        if(url.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return url.get();
    }

    @RequestMapping(path = "/url", method = RequestMethod.GET)
    public List<UrlEntity> findAll() {
        return this.service.findAll();
    }

    @RequestMapping(path = "/url", method = RequestMethod.POST)
    public UrlEntity shorterUrl(@RequestBody UrlDto body) {
        return this.service.shorterUrl(body.defaultUrl());
    }

    @RequestMapping(path = "/url/{id}", method = RequestMethod.PUT)
    public UrlEntity updateShortedUrl(@RequestBody UrlEntity url,
                                      @PathVariable UUID id) {
        return this.service.updateShortedUrl(id, url.getDefaultUrl());
    }
}
