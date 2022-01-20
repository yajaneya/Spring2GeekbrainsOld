package ru.yajaneya.SpringFM1GeekbrainsDz7.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.Product;

public class ProductsSpecifications {
    public static Specification<Product> priceGreaterOrEqualsThan(Integer price) {
        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> priceLessThanOrEqualsThan(Integer price) {
        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> titleLike(String titlePart) {
        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));
    }

    public static Specification<Product> categoryEqual(String categoryNameFilter) {
        System.out.println("Категория для фильтра: '" + categoryNameFilter + "'");
        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("category").get("categoryName"), categoryNameFilter);
    }

}
