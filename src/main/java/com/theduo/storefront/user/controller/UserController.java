package com.theduo.storefront.user.controller;

import com.theduo.storefront.common.response.SuccessResponse;
import com.theduo.storefront.user.dto.ChangePasswordRequest;
import com.theduo.storefront.user.dto.UpdateUserRequest;
import com.theduo.storefront.user.dto.UserDto;
import com.theduo.storefront.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Tag(name = "User")
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @PatchMapping
    public ResponseEntity<SuccessResponse<UserDto>> updateUser(
            @Valid @RequestBody UpdateUserRequest request,
            HttpServletRequest ex
    ) {
        var userDto = userService.updateUser(request);

        return ResponseEntity.ok().body(
                SuccessResponse.of(
                        userDto, "User updated successfully",
                        HttpStatus.OK.value(), ex.getRequestURI()
                )
        );
    }

    @PutMapping("/change-password")
    public ResponseEntity<SuccessResponse<Void>> changePassword(
           @Valid @RequestBody ChangePasswordRequest request,
            HttpServletRequest ex
    ){
        userService.changePassword(request);

        return ResponseEntity.ok().body(
                SuccessResponse.of(null, "Password updated successfully",
                        HttpStatus.OK.value(), ex.getRequestURI()
                )
        );
    }

    @GetMapping("/my-profile")
    public ResponseEntity<SuccessResponse<UserDto>> getMyProfile(
            HttpServletRequest ex
    ) {
        var userDto = userService.getMyProfile();

        return ResponseEntity.ok().body(
                SuccessResponse.of(
                        userDto, "Profile retrieved successfully",
                        HttpStatus.OK.value(), ex.getRequestURI()
                )
        );
    }
}
