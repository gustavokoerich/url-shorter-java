package com.shorter.shorter.controllers;

import com.shorter.shorter.entities.UrlEntity;
import com.shorter.shorter.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RestController
@RequestMapping()
public class RedirectController {

    @Autowired
    UrlService service;

    @RequestMapping(path = "/{shortUrl}", method = RequestMethod.GET)
    public RedirectView redirectByShortUrl(@PathVariable String shortUrl) {
        Optional<UrlEntity> url = this.service.findByShortUrl(shortUrl);
        if(url.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        return new RedirectView(url.get().getDefaultUrl());
    }
}
