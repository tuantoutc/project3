package com.javaweb.repository.custom;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingSearchRequest2;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BuildingRepositoryCustom {
    List<BuildingEntity> findAll(BuildingSearchRequest2 request, Pageable pageable);
}
