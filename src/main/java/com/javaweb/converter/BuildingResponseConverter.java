package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.District;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BuildingResponseConverter {

    @Autowired
    private ModelMapper modelMapper;
    public BuildingSearchResponse toBuildingSearchResponse(BuildingEntity item)
    {
        BuildingSearchResponse building = modelMapper.map(item,BuildingSearchResponse.class);
//        lấy ra name của district từ enum district
        String districtNameEntity = item.getDistrict();
//      gọi đến enum district va hứng bằng 1 map
        Map<String, String > districts = District.type();

        String districtName = "";
        if(districtNameEntity != null && !districtNameEntity.equals(""))
            {
                // lay gia trị name district ra theo map tren
                districtName = districts.get(districtNameEntity);
            }
        building.setAddress(item.getStreet()+", "+item.getWard()+", "+districtName);
        List<RentAreaEntity> listRentArea = item.getRentareas();
        String rentarea = listRentArea.stream().map(i ->i.getValue().toString() ).collect(Collectors.joining(","));
        building.setRentArea(rentarea);

        return building;
    }

}
