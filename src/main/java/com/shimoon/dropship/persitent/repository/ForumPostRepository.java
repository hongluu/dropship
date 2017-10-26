package com.shimoon.dropship.persitent.repository;

import com.shimoon.dropship.persitent.entities.ForumPost;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ForumPostRepository extends MongoRepository<ForumPost, Serializable> {
    void removeAllBySource(String source);
    int countAllBySource(String source);

    List<ForumPost> findTop30BySourceOrderByUpdatedDateDesc(String source);
}
