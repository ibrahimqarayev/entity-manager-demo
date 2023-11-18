package com.ibrahim.entitymanagerdemo.contoller;

import com.ibrahim.entitymanagerdemo.dto.response.BrandResponse;
import com.ibrahim.entitymanagerdemo.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {
    private final BrandService brandService;

    @GetMapping
    public List<BrandResponse> getAll() {
        return brandService.findAll();
    }

}
