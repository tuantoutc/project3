package com.javaweb.service;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.BuildingSearchRequest2;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IBuildingService {
    ResponseDTO listStaff(Long buildingId);
    List<BuildingSearchResponse> findAll(BuildingSearchRequest2 request , int pageNo, int pageSize);
    ResponseDTO addOrUpdateBuilding(BuildingDTO building);
    ResponseDTO deleteBuilding(List<Long> ids);
    BuildingDTO findById(Long id);
    ResponseDTO loadTypeCode(Long id);
    ResponseDTO updateAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO);
}
