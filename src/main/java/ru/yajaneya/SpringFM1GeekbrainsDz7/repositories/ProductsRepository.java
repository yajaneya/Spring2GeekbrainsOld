package ru.yajaneya.SpringFM1GeekbrainsDz7.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository <Product, Long>, JpaSpecificationExecutor<Product> {
    @Query("select p from Product p where p.title = ?1")
    Optional<Product> findByTitle(String title);

    Optional<Product> findById(Long id);



}
