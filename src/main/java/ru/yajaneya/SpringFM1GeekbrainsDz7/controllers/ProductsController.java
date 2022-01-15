package ru.yajaneya.SpringFM1GeekbrainsDz7.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.yajaneya.SpringFM1GeekbrainsDz7.converters.ProductConverter;
import ru.yajaneya.SpringFM1GeekbrainsDz7.dto.ProductDto;
import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.Product;
import ru.yajaneya.SpringFM1GeekbrainsDz7.exceptions.ResourceNotFoundException;
import ru.yajaneya.SpringFM1GeekbrainsDz7.services.ProductsService;
import ru.yajaneya.SpringFM1GeekbrainsDz7.validators.ProductValidator;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductsService productsService;
    private final ProductConverter productConverter;
    private final ProductValidator productValidator;

    @GetMapping
    public Page<ProductDto> getProducts
            (@RequestParam (defaultValue = "1") Integer page,
             @RequestParam (name = "min_price", required = false) Integer minPrice,
             @RequestParam (name = "max_price", required = false) Integer maxPrice) {

        if (page < 1) {
            page=1;
        }
        int totalPages = productsService.findAll(minPrice, maxPrice, page).getTotalPages();
        if (page > totalPages) {
            page = totalPages;
        }

        return productsService.findAll(minPrice, maxPrice, page)
                .map(productConverter::entityToDto);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById (@PathVariable Long id) {
        Product product = productsService.findByID(id)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт с id = " + id + " не найден."));
        return productConverter.entityToDto(product);
    }

    @PostMapping
    public ProductDto saveNewProduct (@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        product.setId(null);
        product = productsService.save(product);
        return productConverter.entityToDto(product);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productsService.update(productDto);
        return productConverter.entityToDto(product);
    }

    @DeleteMapping("/{id}")
    public void delProduct (@PathVariable Long id) {
        productsService.deleteById(id);
    }

}
