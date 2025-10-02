package com.theduo.storefront.product.controller;

import com.theduo.storefront.common.response.ApiResponse;
import com.theduo.storefront.product.dto.CreateProductRequest;
import com.theduo.storefront.product.dto.ProductDto;
import com.theduo.storefront.product.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@AllArgsConstructor
@RestController
@RequestMapping("/product")
@Tag(name = "Product")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductDto>> register(
            @Valid @RequestBody CreateProductRequest request,
            UriComponentsBuilder builder,
            HttpServletRequest req
    ) {
        var productDto = productService.registerProduct(request);

        var uri = builder.path("/product/{id}").buildAndExpand(productDto.getId()).toUri();

        return ResponseEntity.created(uri).body(
                ApiResponse.success(
                        productDto,
                        "Product added successfully",
                        HttpStatus.CREATED.value(),
                        req.getRequestURI()

                )
        );
    }
}
