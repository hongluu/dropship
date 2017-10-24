package com.shimoon.webcrawler.persitent.repository;

import com.shimoon.webcrawler.persitent.entities.ForumConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;


@Repository
public interface ForumConfigRepository extends JpaRepository<ForumConfig, Serializable> {
// List<ForumConfig> findAllByOrOrderByUpdatedDateDesc();


}
