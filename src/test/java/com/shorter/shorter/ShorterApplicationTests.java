package com.shorter.shorter;

import com.shorter.shorter.entities.UrlEntity;
import com.shorter.shorter.repositories.UrlRepository;
import com.shorter.shorter.services.UrlService;
import com.shorter.shorter.utils.UrlUtils;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ShorterApplicationTests  {

	@Autowired
	UrlService service;

	private EntityManager entityManager;

	private UrlUtils utils;

	@Test
	void findAll() {
		UrlEntity newUrl = new UrlEntity("asuhdf", this.utils.generateAleatoryUrl());
		this.service.shorterUrl("asdufhasudf");
		System.out.println(this.service.findAll());
	}


	private UrlEntity createUrl(String defaultUrl) {
		UrlEntity newUrl = new UrlEntity(defaultUrl, this.utils.generateAleatoryUrl());
		this.service.shorterUrl("asdufhasudf");
		return newUrl;
	}

	@Test
	void contextLoads() {
	}

}
