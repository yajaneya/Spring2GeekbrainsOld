package ru.yajaneya.SpringFM1GeekbrainsDz7.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.Product;

public class ProductSpecifications {
    public static Specification<Product> priceGreaterOrEqualsThan (Integer price) {
        return ((root, query, criteriaBuilder)
                -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price));
    }

    public static Specification<Product> priceLessOrEqualsThan (Integer price) {
        return ((root, query, criteriaBuilder)
                -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price));
    }

}
