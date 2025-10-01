package com.theduo.storefront.category.controller;

import com.theduo.storefront.category.dto.CategoryDto;
import com.theduo.storefront.category.dto.CreateCategoryRequest;
import com.theduo.storefront.category.service.CategoryService;
import com.theduo.storefront.common.response.ApiResponse;
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
@RequestMapping("/category")
@Tag(name = "Category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryDto>> createCategory(
            @Valid @RequestBody CreateCategoryRequest request,
            UriComponentsBuilder builder,
            HttpServletRequest req
    ) {
        var categoryDto = categoryService.createCategory(request);

        var uri = builder.path("/category/{id}").buildAndExpand(categoryDto.getId()).toUri();

        return ResponseEntity.created(uri).body(ApiResponse.success(
                categoryDto,
                "Category created successfully",
                HttpStatus.CREATED.value(),
                req.getRequestURI()
        ));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryDto>> updateCategory(
            @PathVariable int categoryId,
            @Valid @RequestBody CreateCategoryRequest request,
            HttpServletRequest req
    ) {
        var categoryDto = categoryService.updateCategory(categoryId,request);

        return ResponseEntity.ok(ApiResponse.success(
                categoryDto,
                "Category updated successfully",
                HttpStatus.OK.value(),
                req.getRequestURI()
        ));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryDto>> getCategoryById(
            @PathVariable int categoryId,
            HttpServletRequest req
    ) {
        var categoryDto = categoryService.getCategoryById(categoryId);

        return ResponseEntity.ok(ApiResponse.success(
                categoryDto,
                "Category retrieved successfully",
                HttpStatus.OK.value(),
                req.getRequestURI()
        ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getAllCategories(
            HttpServletRequest req
    ) {
        var categoryDto = categoryService.getAllCategories();

        return ResponseEntity.ok(ApiResponse.success(
                categoryDto,
                "Categories retrieved successfully",
                HttpStatus.OK.value(),
                req.getRequestURI()

        ));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<Void>> deleteCategoryById(
            @PathVariable int categoryId,
            HttpServletRequest req
    ) {
        categoryService.deleteCategoryById(categoryId);

        return ResponseEntity.ok(
                ApiResponse.success(
                        null,
                        "Category deleted successfully",
                        HttpStatus.OK.value(),
                        req.getRequestURI()
                )
        );
    }


}
