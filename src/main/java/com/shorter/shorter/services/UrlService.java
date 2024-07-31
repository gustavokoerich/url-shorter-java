package com.shorter.shorter.services;
import com.shorter.shorter.entities.UrlEntity;
import com.shorter.shorter.repositories.UrlRepository;
import com.shorter.shorter.utils.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlService {

    @Autowired
    private UrlRepository repository;

    @Autowired
    private CacheManager cacheManager;

    private UrlUtils utils;

    public String redirectByShortUrl(String shortUrl) {
        Optional<UrlEntity> checkExists = this.repository.findByShortUrl(shortUrl);
        if(checkExists.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        return checkExists.get().getDefaultUrl();
    }

    public Optional<UrlEntity> findById(UUID id) {
        return this.repository.findById(id);
    }

    public Optional<UrlEntity> findByShortUrl(String url) {
        return this.repository.findByShortUrl(url);
    }

    public Optional<UrlEntity> findByDefaultUrl(String url) {
        return this.repository.findByDefaultUrl(url);
    }

    public List<UrlEntity> findAll() {
        return this.repository.findAll();
    }

    public UrlEntity shorterUrl(String defaultUrl) {
        Optional<UrlEntity> checkExists = this.repository.findByDefaultUrl(defaultUrl);
        if(checkExists.isPresent()) throw new ResponseStatusException(HttpStatus.CONFLICT);

        UrlEntity url = new UrlEntity(defaultUrl, this.utils.generateAleatoryUrl());
        return this.repository.save(url);
    }

    public UrlEntity updateShortedUrl(UUID id, String defaultUrl) {
        this.repository.findById(id).orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND) );

        String shortUrl = this.utils.generateAleatoryUrl();
        UrlEntity updatedUrl = new UrlEntity(defaultUrl, shortUrl);

        updatedUrl.setId(id);
        return this.repository.save(updatedUrl);
    }

}
