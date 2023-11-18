package com.ibrahim.entitymanagerdemo.service;

import com.ibrahim.entitymanagerdemo.domain.Brand;
import com.ibrahim.entitymanagerdemo.dto.response.BrandResponse;
import com.ibrahim.entitymanagerdemo.exception.ResourceNotFoundException;
import com.ibrahim.entitymanagerdemo.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BrandService {
    private final BrandRepository brandRepository;
    private final DtoConversionService conversionService;

    public List<BrandResponse> findAll() {
        List<Brand> brands = brandRepository.findAll();
        return conversionService.toBrandResponseList(brands);
    }

    public BrandResponse findByName(String name) {
        Brand brand = brandRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with name: " + name));
        return conversionService.toBrandResponse(brand);
    }
}
