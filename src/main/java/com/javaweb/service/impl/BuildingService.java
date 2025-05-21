package com.javaweb.service.impl;

import com.javaweb.converter.BuildingEntityConverterDTO;
import com.javaweb.converter.BuildingResponseConverter;

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
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
//    my older code
//        if(building.getId() == null){
//            BuildingEntity buildingEntity = new BuildingEntity();
//            buildingEntity.setName(building.getName());
//            buildingEntity.setNumberOfBasement(building.getNumberOfBasement());
//            buildingRepository.save(buildingEntity);
//
//            BuildingEntity result = buildingRepository.findByNameEquals(building.getName());
//            building.setId(result.getId());
//
//            saveBuilding(result, building);
//        }
//        else
//        {
//            if(building.getId() != null){
//                BuildingEntity result = buildingRepository.findById(building.getId()).get();
//                rentAreaRepository.deleteByBuildingId(building.getId());
//                saveBuilding(result, building);
//             }
//        }
//        my new code
        BuildingEntity buildingEntity = new BuildingEntity();
        List<UserEntity> staffs = new ArrayList<>();
        if(building.getId() != null){
            buildingEntity = buildingRepository.findById(building.getId()).get();
            staffs = buildingEntity.getStaffsAssBuilding();
        }

        buildingEntity = modelMapper.map(building, BuildingEntity.class);
        buildingEntity.setStaffsAssBuilding(staffs);
        saveBuilding(buildingEntity, building);

        BuildingEntity buildingEntity1 = buildingRepository.findByNameEquals(building.getName());
        building.setId(buildingEntity1.getId());

        rentAreaRepository.deleteByBuildingId(building.getId());
        addRentArea(building.getRentArea(), buildingEntity1);

        buildingRepository.save(buildingEntity1);

        return new ResponseDTO();
    }

    public void saveBuilding(BuildingEntity result, BuildingDTO building) {



        List<String> type = building.getTypeCode();
        String typeCode = type.stream().map(i ->i.toString() ).collect(Collectors.joining(","));
        result.setTypeCode(typeCode);


        buildingRepository.save(result);

    }

    @Override
    @Transactional
    public void deleteBuilding(List<Long> ids) {
//        for(Long id : ids){
//            if(buildingRepository.existsById(id)){
//                rentAreaRepository.deleteByBuildingId(id);
//                BuildingEntity building = buildingRepository.findById(id).get();
//                buildingRepository.deleteById(id);
//            }
//            else{
//                System.out.println("Khong ton tai building id: "+id+"");
//            }
//
//        }
        rentAreaRepository.deleteByBuildingIdIn(ids);
        buildingRepository.deleteByIdIn(ids);
    }
    @Autowired
    private BuildingResponseConverter buildingResponseConverter;

    @Override
    public ResponseDTO listStaff(Long buildingId) {
        BuildingEntity building = buildingRepository.findById(buildingId).get();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "staff");// lay tat ca cac nhan vien dag hoa dong
        List<UserEntity> staffAssigment = building.getStaffsAssBuilding(); // lay tat ca nhan vien quan ly toa nha
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
    public ResponseDTO updateAssignmentBuilding(AssignmentBuildingDTO ab) {

        BuildingEntity building = buildingRepository.findById(ab.getBuildingId()).get();
        building.setStaffsAssBuilding(null);
        List<Long> listStaffs = ab.getStaffs();
        List<UserEntity> staffsAssBuilding = new ArrayList<>();
        if(listStaffs != null && !listStaffs.isEmpty() && !listStaffs.equals("")) {
            for (Long id : listStaffs) {
                UserEntity user = userRepository.findById(id).get();
                staffsAssBuilding.add(user);
            }
            building.setStaffsAssBuilding(staffsAssBuilding);
            buildingRepository.save(building);

        }
        return new ResponseDTO();
    }
    public void addRentArea(String rentArea, BuildingEntity building) {
        List<RentAreaEntity> rentAreaForBuild = new ArrayList<>();
        if(rentArea != null && !rentArea.equals("")){
            String [] values = rentArea.trim().split(",");
            for(String v : values){

                RentAreaEntity rentAreaEntity = new RentAreaEntity();
                rentAreaEntity.setValue( v);
                rentAreaEntity.setBuilding(building);
                rentAreaRepository.save(rentAreaEntity);
                rentAreaForBuild.add(rentAreaEntity);
            }
        }
        building.setRentareas(rentAreaForBuild);
    }
}