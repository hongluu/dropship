package com.shimoon.webcrawler.persitent.repository;

import com.shimoon.webcrawler.persitent.entities.ForumPost;
import com.shimoon.webcrawler.persitent.entities.UserLogin;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ForumPostRepository extends JpaRepository<ForumPost, Serializable> {

}
