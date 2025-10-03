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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductDto>>> getAllProducts(
            @RequestParam(required = false, name = "categoryId") Byte categoryId,
            HttpServletRequest req
    ){
        var productDtos = productService.getAllProducts(categoryId);

        return ResponseEntity.ok(ApiResponse.success(
                productDtos,
                "All products retrieved successfully",
                HttpStatus.OK.value(),
                req.getRequestURI()

        ));

    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<ProductDto>> getProduct(
            @PathVariable Byte categoryId,
            HttpServletRequest req
    ){
        return null;
    }
}
