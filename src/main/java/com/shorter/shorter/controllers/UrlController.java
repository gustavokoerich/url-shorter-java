package com.shorter.shorter.controllers;

import com.shorter.shorter.dtos.UrlDto;
import com.shorter.shorter.entities.UrlEntity;
import com.shorter.shorter.services.UrlService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("/api/v1")
public class UrlController {

    @Autowired
    UrlService service;

    @Autowired
    CacheManager cacheManager;

    private static Logger logger = LoggerFactory.getLogger(UrlController.class);

    @Cacheable(key = "#id", value = "url")
    @RequestMapping(path = "/url/{id}", method = RequestMethod.GET)
    public Optional<UrlEntity> findById(@PathVariable UUID id) {
        logger.info("Find by id {}", id);
        return this.service.findById(id);
    }

    @Cacheable(value = "url", key = "#shortUrl")
    @RequestMapping(path = "/short/{shortUrl}", method = RequestMethod.GET)
    public Optional<UrlEntity> findByShortUrl(@PathVariable String shortUrl) {
        logger.info("Find by shortUrl {}", shortUrl);
        return this.service.findByShortUrl(shortUrl);
    }

    @Cacheable(value = "url", key = "#body.defaultUrl")
    @RequestMapping(path = "/default", method = RequestMethod.POST)
    public Optional<UrlEntity> findByDefaultUrl(@RequestBody UrlDto body) {
        logger.info("Find by defaultUrl {}", body.defaultUrl());
        return this.service.findByDefaultUrl(body.defaultUrl());
    }

    @Cacheable(value = "url")
    @RequestMapping(path = "/url", method = RequestMethod.GET)
    public List<UrlEntity> findAll() {
        return this.service.findAll();
    }

    @RequestMapping(path = "/url", method = RequestMethod.POST)
    public UrlEntity shorterUrl(@RequestBody UrlDto body) {
        UrlEntity url = this.service.shorterUrl(body.defaultUrl());
        logger.info("Url shorted from {} to {}", body.defaultUrl(), url.getShortUrl());
        return url;
    }


    @Caching(evict = {
            @CacheEvict(value = "url", key = "#id"),
            @CacheEvict(value = "url", key = "#url.shortUrl"),
            @CacheEvict(value = "url", key = "#url.defaultUrl")
    })
    @RequestMapping(path = "/url/{id}", method = RequestMethod.PUT)
    public UrlEntity updateShortedUrl(@NotNull  @RequestBody UrlDto url,
                                      @PathVariable UUID id) {
        UrlEntity updatedUrl = this.service.updateShortedUrl(id, url.defaultUrl());
        logger.info("Url id {} updated", id);
        return updatedUrl;
    }
}
