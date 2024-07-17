package edu.quattrinh.webservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import edu.quattrinh.webservice.dto.request.ApiResponse;
import edu.quattrinh.webservice.dto.request.RoleRequest;
import edu.quattrinh.webservice.dto.response.RoleResponse;
import edu.quattrinh.webservice.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleController {
    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

    @DeleteMapping("/{permission}")
    ApiResponse<Void> delete(@PathVariable String permission) {
        roleService.delete(permission);
        return ApiResponse.<Void>builder().build();
    }
}
