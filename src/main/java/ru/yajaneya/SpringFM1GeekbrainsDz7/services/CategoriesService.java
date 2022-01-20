package ru.yajaneya.SpringFM1GeekbrainsDz7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.Category;
import ru.yajaneya.SpringFM1GeekbrainsDz7.repositories.CategoriesRepository;
import ru.yajaneya.SpringFM1GeekbrainsDz7.soap.categories.CategorySoap;

import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CategoriesService {
    private final CategoriesRepository categoriesRepository;

    public Optional<Category> findByCategoryName (String name) {
        return categoriesRepository.findByCategoryName(name);
    }

    //    SOAP
    public static final Function<Category, CategorySoap> functionEntityToSoap = cS -> {
        CategorySoap c = new CategorySoap();
        c.setCategoryName(cS.getCategoryName());
        cS.getProducts().stream().map(ProductsService.functionEntityToSoap).forEach(p -> c.getProducts().add(p));
        return c;
    };

    //    SOAP
    public CategorySoap getByCategoryName(String title) {
        return categoriesRepository.findByCategoryName(title).
                map(functionEntityToSoap).get();
    }

}
