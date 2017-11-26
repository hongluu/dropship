package com.shimoon.dropship.persitent.repository;

import com.shimoon.dropship.persitent.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Serializable> {

    List<Category> findAllBySource(String source);
    Category findByCode(String category_code);

    @Transactional
    void deleteCategoryBySource(String source);


}
