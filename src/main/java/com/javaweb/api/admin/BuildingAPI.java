package com.javaweb.api.admin;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.IBuildingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {

    @Autowired
    private IBuildingService buildingService;
    @PostMapping
    public ResponseDTO AddOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO)
    {
        ResponseDTO result = new ResponseDTO();
        buildingService.addOrUpdateBuilding(buildingDTO);
    return result;

    }
    @DeleteMapping(value = "/{ids}")
    public ResponseDTO deleteBuilding(@PathVariable List<Long> ids)
    {
    // xuong db de xoa du lieu
        buildingService.deleteBuilding(ids);
        ResponseDTO result = new ResponseDTO();
        return result;
    }
    @GetMapping(value = "/{id}/staffs")
    public ResponseDTO loadStaffs(@PathVariable Long id)
    {
        ResponseDTO result = buildingService.listStaff(id);
        return result;
    }
//    @GetMapping(value = "/{id}/typecode")
//    public ResponseDTO loadTypeCode(@PathVariable Long id)
//    {
//        ResponseDTO result = buildingService.listStaff(id);
//        return result;
//    }


    @PostMapping (value = "/assingment")
    public ResponseDTO updateAssingmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO)
    {
       ResponseDTO result =   buildingService.updateAssignmentBuilding(assignmentBuildingDTO);
        return result;
    }

    @GetMapping(value = "/type/{id}")
    public ResponseDTO loadTypeCode(@PathVariable Long id)
    {
        ResponseDTO result = buildingService.loadTypeCode(id);
        return result;
    }






}
