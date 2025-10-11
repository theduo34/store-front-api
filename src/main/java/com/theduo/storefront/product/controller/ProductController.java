package com.theduo.storefront.product.controller;

import com.theduo.storefront.common.response.SuccessResponse;
import com.theduo.storefront.product.dto.CreateProductRequest;
import com.theduo.storefront.product.dto.ProductDto;
import com.theduo.storefront.product.dto.UpdateProductRequest;
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
    public ResponseEntity<SuccessResponse<ProductDto>> register(
            @Valid @RequestBody CreateProductRequest request,
            UriComponentsBuilder builder,
            HttpServletRequest req
    ) {
        var productDto = productService.registerProduct(request);

        var uri = builder.path("/product/{id}").buildAndExpand(productDto.getId()).toUri();

        return ResponseEntity.created(uri).body(
                SuccessResponse.of(
                        productDto,
                        "Product added successfully",
                        HttpStatus.CREATED.value(),
                        req.getRequestURI()

                )
        );
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<ProductDto>>> getAllProducts(
            @RequestParam(required = false, name = "categoryId") Byte categoryId,
            HttpServletRequest req
    ){
        var productDtos = productService.getAllProducts(categoryId);

        return ResponseEntity.ok(SuccessResponse.of(
                productDtos,
                "All products retrieved successfully",
                HttpStatus.OK.value(),
                req.getRequestURI()

        ));

    }

    @GetMapping("/{productId}")
    public ResponseEntity<SuccessResponse<ProductDto>> getProduct(
            @PathVariable Long productId,
            HttpServletRequest req
    ){
        var productDto = productService.getProductById(productId);

        return ResponseEntity.ok(SuccessResponse.of(
                productDto,
                "Product retrieved successfully",
                HttpStatus.OK.value(),
                req.getRequestURI()
        ));
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<SuccessResponse<ProductDto>> updateProduct(
            @PathVariable Long productId,
            @Valid @RequestBody UpdateProductRequest request,
            HttpServletRequest req
    ){
        var productDto = productService.updateProduct(productId, request);
        return ResponseEntity.ok(SuccessResponse.of(
                productDto,
                "Product updated successfully",
                HttpStatus.OK.value(),
                req.getRequestURI()
        ));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<SuccessResponse<Void>> deleteProduct(
            @PathVariable Long productId,
            HttpServletRequest req
    ){
        productService.deleteProduct(productId);
        return ResponseEntity.ok(SuccessResponse.of(
                null,
                "Product deleted successfully",
                HttpStatus.OK.value(),
                req.getRequestURI()
        ));
    }
}
