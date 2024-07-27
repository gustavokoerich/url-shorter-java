package com.shorter.shorter.services;
import com.shorter.shorter.entities.UrlEntity;
import com.shorter.shorter.repositories.UrlRepository;
import com.shorter.shorter.utils.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    private UrlUtils utils;

    public List<UrlEntity> findAll() {
        return this.repository.findAll();
    }

    public Optional<UrlEntity> findById(UUID id) {
        return this.repository.findById(id);
    }

    public UrlEntity shorterUrl(String defaultUrl) {
        StringBuilder shortUrl = this.utils.generateAleatoryUrl();
        UrlEntity url = new UrlEntity(defaultUrl, shortUrl);
        this.repository.save(url);
        return url;
    }

    public UrlEntity updateShortedUrl(UUID id, String defaultUrl) {
        this.repository.findById(id).orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND) );

        StringBuilder shortUrl = this.utils.generateAleatoryUrl();
        UrlEntity updatedUrl = new UrlEntity(defaultUrl, shortUrl);
        updatedUrl.setId(id);
        return this.repository.save(updatedUrl);
    }

}
