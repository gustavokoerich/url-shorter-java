package com.shorter.shorter.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.Optional;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class UrlEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private StringBuilder shortUrl;

    private String defaultUrl;

    public UrlEntity(String defaultUrl, StringBuilder shortUrl) {
        this.defaultUrl = defaultUrl;
        this.shortUrl = shortUrl;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }

    public void setShortUrl(StringBuilder shortUrl) {
        this.shortUrl = shortUrl;
    }

    public UUID getId() {
        return id;
    }

    public String getDefaultUrl() {
        return defaultUrl;
    }

    public StringBuilder getShortUrl() {
        return shortUrl;
    }

}
