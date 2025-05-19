package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.District;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingResponseConverter {

    @Autowired
    private ModelMapper modelMapper;
    public BuildingSearchResponse toBuildingSearchResponse(BuildingEntity item)
    {
        BuildingSearchResponse building = modelMapper.map(item,BuildingSearchResponse.class);
        String districtNameEntity = item.getDistrict();
        String districtValue = "";
        if(districtNameEntity!=null && !districtNameEntity.equals(""))
            {
                try {
                    District district = District.valueOf(districtNameEntity);
                    districtValue = district.districtName;
                } catch (IllegalArgumentException e) {
                    System.out.println("Khong tim thay enum district phu hop");

                }
            }
        building.setAddress(item.getStreet()+", "+item.getWard()+", "+districtValue);
        List<RentAreaEntity> listRentArea = item.getRentareas();
        String rentarea = listRentArea.stream().map(i ->i.getValue().toString() ).collect(Collectors.joining(","));
        building.setRentArea(rentarea);


        return building;
    }

}
