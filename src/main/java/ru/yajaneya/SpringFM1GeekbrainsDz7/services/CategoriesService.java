package ru.yajaneya.SpringFM1GeekbrainsDz7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.Category;
import ru.yajaneya.SpringFM1GeekbrainsDz7.repositories.CategoriesRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriesService {
    private final CategoriesRepository categoriesRepository;

    public Optional<Category> findByCategoryName (String name) {
        return categoriesRepository.findByCategoryName(name);
    }
}
