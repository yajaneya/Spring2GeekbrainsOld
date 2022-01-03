package ru.yajaneya.SpringFM1GeekbrainsDz7.converters;

import org.springframework.stereotype.Component;
import ru.yajaneya.SpringFM1GeekbrainsDz7.dto.ProductDto;
import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.Product;

@Component
public class ProductConverter {

    public Product dtoToEntity (ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getPrice());
    }

    public ProductDto entityToDto (Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice());
    }

}
