package com.shimoon.webcrawler.persitent.repository;

import com.shimoon.webcrawler.persitent.entities.ForumPost;
import com.shimoon.webcrawler.persitent.entities.UserLogin;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ForumPostRepository extends MongoRepository<ForumPost, Serializable> {
    void removeAllBySource(String source);
    int countAllBySource(String source);

    List<ForumPost> findTop100BySourceOrderByUpdatedDateDesc(String source);
}
