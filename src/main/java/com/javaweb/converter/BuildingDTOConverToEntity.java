package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BuildingDTOConverToEntity {
    @Autowired
    private ModelMapper modelMapper;
    public BuildingEntity coverterToEntity(BuildingDTO item){
        BuildingEntity result = modelMapper.map(item, BuildingEntity.class);
        List<RentAreaEntity> rentArea = new ArrayList<>();
        if(item.getRentArea() != null && !item.getRentArea().equals("")){
            String [] value = item.getRentArea().split(",");
            for(String v : value){
                RentAreaEntity entity = new RentAreaEntity();

            }
        }

        return null;

    }


}
