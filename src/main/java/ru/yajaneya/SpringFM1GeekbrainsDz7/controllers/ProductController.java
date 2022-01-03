package ru.yajaneya.SpringFM1GeekbrainsDz7.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.yajaneya.SpringFM1GeekbrainsDz7.converters.ProductConverter;
import ru.yajaneya.SpringFM1GeekbrainsDz7.dto.ProductDto;
import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.Product;
import ru.yajaneya.SpringFM1GeekbrainsDz7.exceptions.ResourceNotFoundException;
import ru.yajaneya.SpringFM1GeekbrainsDz7.services.ProductService;
import ru.yajaneya.SpringFM1GeekbrainsDz7.validators.ProductValidator;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
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
        int totalPages = productService.findAll(minPrice, maxPrice, page).getTotalPages();
        if (page > totalPages) {
            page = totalPages;
        }

        return productService.findAll(minPrice, maxPrice, page)
                .map(productConverter::entityToDto);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById (@PathVariable Long id) {
        Product product = productService.findByID(id)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт с id = " + id + " не найден."));
        return productConverter.entityToDto(product);
    }

    @PostMapping
    public ProductDto saveNewProduct (@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        product.setId(null);
        product = productService.save(product);
        return productConverter.entityToDto(product);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productService.update(productDto);
        return productConverter.entityToDto(product);
    }

    @DeleteMapping("/{id}")
    public void delProduct (@PathVariable Long id) {
        productService.deleteById(id);
    }

}
