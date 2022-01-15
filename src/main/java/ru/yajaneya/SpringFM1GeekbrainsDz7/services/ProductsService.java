package ru.yajaneya.SpringFM1GeekbrainsDz7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import ru.yajaneya.SpringFM1GeekbrainsDz7.dto.ProductDto;
import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.Product;
import org.springframework.stereotype.Service;
import ru.yajaneya.SpringFM1GeekbrainsDz7.exceptions.ResourceNotFoundException;
import ru.yajaneya.SpringFM1GeekbrainsDz7.repositories.ProductsRepository;
import ru.yajaneya.SpringFM1GeekbrainsDz7.repositories.specifications.ProductsSpecifications;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductsRepository productsRepository;

    public Page<Product> findAll(Integer minPrice, Integer maxPrice, String partTitle, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductsSpecifications.priceLessThanOrEqualsThan(maxPrice));
        }
        if (partTitle != null) {
            spec = spec.and(ProductsSpecifications.titleLike(partTitle));
        }

        return productsRepository.findAll(spec, PageRequest.of(page - 1, 8));
    }

    public Optional<Product> findByID (Long id) {
        return productsRepository.findById(id);
    }

    public Product save (Product product) {
        return productsRepository.save(product);
    }

    @Transactional
    public Product update (ProductDto productDto) {
        Long id = productDto.getId();
        Product product = findByID(id)
                .orElseThrow(() -> new ResourceNotFoundException("Невозможно обновить. Продукт с id = " + id + " не найден."));
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        return product;
    }


    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

}
