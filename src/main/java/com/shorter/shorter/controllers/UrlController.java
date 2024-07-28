package com.shorter.shorter.controllers;

import com.shorter.shorter.dtos.UrlDto;
import com.shorter.shorter.entities.UrlEntity;
import com.shorter.shorter.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("/api/v1")
public class UrlController {

    @Autowired
    UrlService service;

    @Cacheable(key = "#id", value = "url")
    @RequestMapping(path = "/url/{id}", method = RequestMethod.GET)
    public Optional<UrlEntity> findById(@PathVariable UUID id) {
        return this.service.findById(id);
    }

    @Cacheable(value = "url", key = "#shortUrl")
    @RequestMapping(path = "/short/{shortUrl}", method = RequestMethod.GET)
    public Optional<UrlEntity> findByShortUrl(@PathVariable String shortUrl) {
        return this.service.findByShortUrl(shortUrl);
    }

    @Cacheable(value = "url", key = "#body.defaultUrl")
    @RequestMapping(path = "/default", method = RequestMethod.POST)
    public Optional<UrlEntity> findByDefaultUrl(@RequestBody UrlDto body) {
        return this.service.findByDefaultUrl(body.defaultUrl());
    }

    @Cacheable(value = "url")
    @RequestMapping(path = "/url", method = RequestMethod.GET)
    public List<UrlEntity> findAll() {
        return this.service.findAll();
    }

    @RequestMapping(path = "/url", method = RequestMethod.POST)
    public UrlEntity shorterUrl(@RequestBody UrlDto body) {
        return this.service.shorterUrl(body.defaultUrl());
    }

    @CachePut(key = "#id + #url.defaultUrl +#url.shortUrl", value = "url")
    @RequestMapping(path = "/url/{id}", method = RequestMethod.PUT)
    public UrlEntity updateShortedUrl(@RequestBody UrlEntity url,
                                      @PathVariable UUID id) {
        String shortUrl = url.getShortUrl();
        String defaultUrl = url.getDefaultUrl();
        return this.service.updateShortedUrl(id, url.getDefaultUrl());
    }
}
