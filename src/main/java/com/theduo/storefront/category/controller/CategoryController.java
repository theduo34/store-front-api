package com.theduo.storefront.category.controller;

import com.theduo.storefront.category.dto.CategoryDto;
import com.theduo.storefront.category.dto.CreateCategoryRequest;
import com.theduo.storefront.category.service.CategoryService;
import com.theduo.storefront.common.exception.CategoryExistByNameException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/category")
@Tag(name = "Category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(
            @Valid @RequestBody CreateCategoryRequest request,
            UriComponentsBuilder builder
    ) {
        var categoryDto = categoryService.createCategory(request);

        var uri = builder.path("/category/{id}").buildAndExpand(categoryDto.getId()).toUri();

        return ResponseEntity.created(uri).body(categoryDto);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable int categoryId,
            @Valid @RequestBody CreateCategoryRequest request
    ) {
        var categoryDto = categoryService.updateCategory(categoryId,request);

        return ResponseEntity.ok().body(categoryDto);
    }

    @ExceptionHandler(CategoryExistByNameException.class)
    public ResponseEntity<Map<String, String>> handleCategoryExistByNameException() {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                Map.of("name", "Category already exists")
        );
    }
}
