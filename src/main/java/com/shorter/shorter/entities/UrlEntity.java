package com.shorter.shorter.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "url")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class UrlEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true)
    private String shortUrl;

    @Column(unique = true)
    private String defaultUrl;

    public UrlEntity(String defaultUrl, String shortUrl) {
        this.defaultUrl = defaultUrl;
        this.shortUrl = shortUrl;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public UUID getId() {
        return id;
    }

    public String getDefaultUrl() {
        return defaultUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

}