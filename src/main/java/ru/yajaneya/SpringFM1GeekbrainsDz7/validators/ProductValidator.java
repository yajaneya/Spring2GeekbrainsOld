package ru.yajaneya.SpringFM1GeekbrainsDz7.validators;

import org.springframework.stereotype.Component;
import ru.yajaneya.SpringFM1GeekbrainsDz7.dto.ProductDto;
import ru.yajaneya.SpringFM1GeekbrainsDz7.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    public void validate (ProductDto productDto) {
        List<String> errors = new ArrayList<>();
        if (productDto.getPrice() <= 0) {
            errors.add("Цена продукта должна быть больше 0");
        }
        if (productDto.getTitle().isBlank()) {
            errors.add("Продукт должнен иметь название");
        }
        if(!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
