package com.shorter.shorter.repositories;

import com.shorter.shorter.entities.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, UUID> {
    @Query(value = "select * from url where short_url = ?1", nativeQuery = true)
    Optional<UrlEntity> findByShortUrl(String url);

    @Query(value = "select * from url where default_url = ?1", nativeQuery = true)
    Optional<UrlEntity> findByDefaultUrl(String url);
}
