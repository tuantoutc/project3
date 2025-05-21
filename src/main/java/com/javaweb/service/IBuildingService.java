package com.javaweb.service;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.BuildingSearchRequest2;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;

import java.util.List;

public interface IBuildingService {
    ResponseDTO listStaff(Long buildingId);
    List<BuildingSearchResponse> findAll(BuildingSearchRequest2 request);
    ResponseDTO addOrUpdateBuilding(BuildingDTO building);
    void deleteBuilding(List<Long> ids);
    BuildingDTO findById(Long id);
    ResponseDTO loadTypeCode(Long id);
    ResponseDTO updateAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO);
}
