package com.javaweb.service.impl;

import com.javaweb.converter.BuildingEntityConverterDTO;
import com.javaweb.converter.BuildingResponseConverter;
import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.BuildingSearchRequest2;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;

import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.model.response.TypeCodeResponseDTO;
import com.javaweb.repository.AssignmentBuildingRespository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class BuildingService implements IBuildingService {


    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private AssignmentBuildingRespository assignmentBuildingRespository;


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BuildingEntityConverterDTO buildingEntityConverterDTO;


    @Override
    public BuildingDTO findById(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id).get();
        BuildingDTO buildingDTO = buildingEntityConverterDTO.coverterToDTO(buildingEntity);
        return buildingDTO;
    }
    @Override
    @Transactional
    public ResponseDTO addOrUpdateBuilding(BuildingDTO building) {
        BuildingEntity buildingEntity = new BuildingEntity();
        if(building.getId() == null){
            buildingEntity.setName(building.getName());
            buildingEntity.setNumberOfBasement(building.getNumberOfBasement());
            buildingRepository.save(buildingEntity);
            BuildingEntity result = buildingRepository.findByNameEquals(building.getName());
            building.setId(result.getId());
            result = modelMapper.map(building, BuildingEntity.class);
            List<RentAreaEntity> rentArea = new ArrayList<>();
            if(building.getRentArea() != null && !building.getRentArea().equals("")){
                String [] value = building.getRentArea().split(",");
                for(String v : value){
                    RentAreaEntity entity = new RentAreaEntity();
                    entity.setBuilding(result);
                    entity.setValue(v);
                    rentArea.add(entity);
                    rentAreaRepository.save(entity);
                }
            }
            List<String> type = building.getTypeCode();
            String typeCode = type.stream().map(i ->i.toString() ).collect(Collectors.joining(","));
            result.setTypeCode(typeCode);
            result.setRentareas(rentArea);
            buildingRepository.save(result);
        }
        if(building.getId() != null){
            BuildingEntity result = buildingRepository.findById(building.getId()).get();

            result = modelMapper.map(building, BuildingEntity.class);
            List<RentAreaEntity> rentArea = new ArrayList<>();
            rentAreaRepository.deleteByBuildingId(building.getId());
            assignmentBuildingRespository.deleteByBuildingId(building.getId());
            if(building.getRentArea() != null && !building.getRentArea().equals("")){
                String [] value = building.getRentArea().split(",");
                for(String v : value){
                    RentAreaEntity entity = new RentAreaEntity();
                    entity.setBuilding(result);
                    entity.setValue(v);
                    rentArea.add(entity);
                    rentAreaRepository.save(entity);


                }
            }
            List<String> type = building.getTypeCode();
            String typeCode = type.stream().map(i ->i.toString() ).collect(Collectors.joining(","));
            result.setTypeCode(typeCode);
            result.setRentareas(rentArea);
            buildingRepository.save(result);
        }

        return new ResponseDTO();
    }

    @Override
    @Transactional
    public void deleteBuilding(List<Long> ids) {
        for(Long id : ids){
            if(buildingRepository.existsById(id)){
                rentAreaRepository.deleteByBuildingId(id);
                assignmentBuildingRespository.deleteByBuildingId(id);
                buildingRepository.deleteById(id);
            }
            else{
                System.out.println("Khong ton tai building id: "+id+"");
            }

        }
    }
    @Autowired
    private BuildingResponseConverter buildingResponseConverter;

    @Override
    public ResponseDTO listStaff(Long buildingId) {
        BuildingEntity building = buildingRepository.findById(buildingId).get();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "staff");// lay tat ca cac nhan vien dag hoa dong
        List<UserEntity> staffAssigment = assignmentBuildingRespository.findStaffByBuildingId(buildingId); // lay tat ca nhan vien quan ly toa nha
        List<StaffResponseDTO> staffResponseDTOs = new ArrayList<>();// tao mag rong cac nhan vien quan ly de tra ve
        ResponseDTO responseDTO = new ResponseDTO();// toa du lieu tra ra cho view

        // bat dau kiem tra 2 mag. o mag nao ma co nhan vien trug(dang quan ly toa nha) thi danh checked con lai ko
        for(UserEntity it : staffs){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(it.getId());
            staffResponseDTO.setFullName(it.getFullName());
            if(staffAssigment.contains(it)){
                staffResponseDTO.setChecked("checked");
            }
            else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOs.add(staffResponseDTO);// sau khi xog day vao mag de tra du lieu ra
        }
        responseDTO.setData(staffResponseDTOs);
        responseDTO.setMessage("success");

        return responseDTO;
    }

    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest2 request) {
        List<BuildingEntity> buildingEntities =buildingRepository.findAll(request);
        List<BuildingSearchResponse> result = new ArrayList<>();

        for(BuildingEntity item: buildingEntities)
        {
            BuildingSearchResponse building = buildingResponseConverter.toBuildingSearchResponse(item);
            result.add(building);
        }
        return result;
    }
    @Override
    public ResponseDTO loadTypeCode(Long id) {
        BuildingEntity building = buildingRepository.findById(id).get();
        List<String> type = new ArrayList<>();
        if(building.getTypeCode() != null && !building.getTypeCode().equals("")){
            String [] value = building.getTypeCode().split(",");
            for(String v : value){
                type.add(v);
            }
        }
        ResponseDTO responseDTO = new ResponseDTO();// tạo dữ liệu trả về cho view
        List<TypeCodeResponseDTO> listTypeCode = new ArrayList<>();
        Map<String, String> typeCodeMap = TypeCode.type();
        
        // Tạo TypeCodeResponseDTO cho mỗi giá trị enum và kiểm tra xem nó có tồn tại trong danh sách loại tòa nhà không
        for (Map.Entry<String, String> entry : typeCodeMap.entrySet()) {
            TypeCodeResponseDTO typeCodeDTO = new TypeCodeResponseDTO();
            typeCodeDTO.setCode(entry.getKey());
            typeCodeDTO.setName(entry.getValue());
            
            // Kiểm tra nếu loại này có trong danh sách type của tòa nhà
            if (type.contains(entry.getKey())) {
                typeCodeDTO.setChecked("checked");
            } else {
                typeCodeDTO.setChecked("");
            }
            
            listTypeCode.add(typeCodeDTO);
        }
        
        responseDTO.setData(listTypeCode);
        responseDTO.setMessage("success");
        
        return responseDTO;
    }
    @Override
    @Transactional
    public ResponseDTO UpdateAssignmentBuilding(AssignmentBuildingDTO ab) {

        BuildingEntity building = buildingRepository.findById(ab.getBuildingId()).get();
        assignmentBuildingRespository.deleteByBuildingId(ab.getBuildingId());
        List<Long> listStaffs = ab.getStaffs();
        List<AssignmentBuildingEntity> listAssignmentBuilding = new ArrayList<>();
        if(listStaffs != null && !listStaffs.isEmpty() && !listStaffs.equals("")) {
            for (Long id : listStaffs) {
                AssignmentBuildingEntity a = new AssignmentBuildingEntity();
                a.setBuilding(building);
                a.setStaff(userRepository.findById(id).get());
                assignmentBuildingRespository.save(a);
                listAssignmentBuilding.add(a);
            }
            building.setBuildassstaff(listAssignmentBuilding);
            buildingRepository.save(building);

        }
        return new ResponseDTO();
    }
}