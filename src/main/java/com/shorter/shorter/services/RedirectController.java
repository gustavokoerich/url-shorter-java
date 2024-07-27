package com.shorter.shorter.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping()
public class RedirectController {

    @Autowired
    UrlService service;

    @RequestMapping(path = "/{shortUrl}", method = RequestMethod.GET)
    public RedirectView redirectByShortUrl(@PathVariable String shortUrl) {
        return new RedirectView(this.service.redirectByShortUrl(shortUrl));
    }
}
