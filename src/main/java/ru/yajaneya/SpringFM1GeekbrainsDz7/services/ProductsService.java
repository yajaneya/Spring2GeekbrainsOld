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
import ru.yajaneya.SpringFM1GeekbrainsDz7.soap.products.ProductSoap;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductsRepository productsRepository;

    public Page<Product> findAll(
            Integer minPrice,
            Integer maxPrice,
            String partTitle,
            String categoryName,
            Integer page) {

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
        if (categoryName != null) {
            if (!categoryName.equals("")) {
                spec = spec.and(ProductsSpecifications.categoryEqual(categoryName));
            }
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

//    SOAP
    public static final Function<Product, ProductSoap> functionEntityToSoap = p -> {
        ProductSoap pS = new ProductSoap();
        pS.setId(p.getId());
        pS.setTitle(p.getTitle());
        pS.setPrice(p.getPrice());
        pS.setCategoryTitle(p.getCategory().getCategoryName());
        return pS;
    };

    //    SOAP
    public List<ProductSoap> getAllProducts() {
        return productsRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }

    //    SOAP
    public ProductSoap getById(Long id) {
        return productsRepository.findById(id).map(functionEntityToSoap).get();
    }

}
