package ru.yajaneya.SpringFM1GeekbrainsDz7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.Category;

import java.util.Optional;

@Repository
public interface CategoriesRepository extends JpaRepository <Category, Long> {
    Optional<Category> findByCategoryName(String name);
}
