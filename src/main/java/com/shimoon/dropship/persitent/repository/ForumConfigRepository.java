package com.shimoon.dropship.persitent.repository;

import com.shimoon.dropship.persitent.entities.ForumConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;


@Repository
public interface ForumConfigRepository extends JpaRepository<ForumConfig, Serializable> {
// List<ForumConfig> findAllByOrOrderByUpdatedDateDesc();


}
