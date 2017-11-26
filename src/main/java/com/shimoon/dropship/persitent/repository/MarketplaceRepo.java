package com.shimoon.dropship.persitent.repository;

import com.shimoon.dropship.persitent.entities.Category;
import com.shimoon.dropship.persitent.entities.MarketPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


@Repository
public interface MarketplaceRepo extends JpaRepository<MarketPlace, Serializable> {
// List<ForumConfig> findAllByOrOrderByUpdatedDateDesc();

    @Transactional
    void deleteMarketPlaceBySource(String source);
    List<MarketPlace> findAllBySource(String source);

}
