package com.javaweb.controller.admin;
import com.javaweb.enums.District;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.BuildingSearchRequest2;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.TypeCodeResponseDTO;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IBuildingService buildingService;
    @Autowired
    private IBuildingService iBuildingService;


    @GetMapping(value="/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute BuildingSearchRequest2 newBuild, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("modelSearch", newBuild);
        // xuong db và lấy dự liệu lên
        List<BuildingSearchResponse> responseList = buildingService.findAll(newBuild);
        mav.addObject("buildingList", responseList);
        mav.addObject("listStaffs",userService.getStaffs());
        mav.addObject("districts", District.type());
        mav.addObject("typeCodes", TypeCode.type());
        return mav;
    }
    @GetMapping(value="/admin/building-edit")
    public ModelAndView addBuilding(@ModelAttribute("buildingEdit") BuildingDTO buildingDTO, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("districts", District.type());
        mav.addObject("typeCodes", TypeCode.type());
        return mav;
    }
    @GetMapping(value="/admin/building-edit-{id}")
    public ModelAndView editBuilding(@PathVariable("id") Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
    
        // Lấy thông tin tòa nhà theo id
        BuildingDTO buildingDTO = iBuildingService.findById(id);
    
        // Lấy thông tin typeCode với trạng thái checked
        ResponseDTO typeCodeResponse = iBuildingService.loadTypeCode(id);
    
        mav.addObject("buildingEdit", buildingDTO);
        mav.addObject("districts", District.type());
        mav.addObject("typeCodes", TypeCode.type());
        mav.addObject("typeCodeData", typeCodeResponse.getData());
    
        return mav;
    }
}