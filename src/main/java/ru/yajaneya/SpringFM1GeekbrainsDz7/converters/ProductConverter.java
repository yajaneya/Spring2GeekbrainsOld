package ru.yajaneya.SpringFM1GeekbrainsDz7.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yajaneya.SpringFM1GeekbrainsDz7.dto.ProductDto;
import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.Product;
import ru.yajaneya.SpringFM1GeekbrainsDz7.exceptions.ResourceNotFoundException;
import ru.yajaneya.SpringFM1GeekbrainsDz7.services.CategoriesService;

@Component
@RequiredArgsConstructor
public class ProductConverter {
    private final CategoriesService categoriesService;

    public Product dtoToEntity (ProductDto productDto) {
        return new Product(
                productDto.getId(),
                productDto.getTitle(),
                productDto.getPrice(),
                categoriesService.findByCategoryName(productDto.getCategory())
                        .orElseThrow(() -> new ResourceNotFoundException
                                ("Категория " + productDto.getCategory() + " не найдена.")));
    }

    public ProductDto entityToDto (Product product) {
        return new ProductDto(
                product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getCategory().getCategoryName());
    }

}
