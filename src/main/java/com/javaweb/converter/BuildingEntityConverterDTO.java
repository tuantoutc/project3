package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingEntityConverterDTO {
    @Autowired
    private ModelMapper modelMapper;
    public BuildingDTO coverterToDTO(BuildingEntity entity){
        BuildingDTO result = modelMapper.map(entity, BuildingDTO.class);
        List<RentAreaEntity> listRentArea = entity.getRentareas();
        String rentarea = listRentArea.stream().map(i ->i.getValue().toString() ).collect(Collectors.joining(","));
        result.setRentArea(rentarea);
        
        // Xử lý typeCode để chuyển từ chuỗi sang list
        if(entity.getTypeCode() != null && !entity.getTypeCode().isEmpty()){
            String[] type = entity.getTypeCode().split(",");
            List<String> listTypeCode = Arrays.stream(type)
                    .map(String::trim) // Loại bỏ khoảng trắng
                    .filter(s -> !s.isEmpty()) // Lọc bỏ chuỗi rỗng
                    .collect(Collectors.toList());
            result.setTypeCode(listTypeCode);
        }
        
        return result;
    }

}