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
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import org.hibernate.internal.CoreLogging;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private ModelMapper modelMapper;

    @Autowired
    private BuildingEntityConverterDTO buildingEntityConverterDTO;

    @Autowired
    private BuildingResponseConverter buildingResponseConverter;

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

        if(building.getId() != null){
            buildingEntity = buildingRepository.findById(building.getId()).get();
        }
        buildingEntity = modelMapper.map(building, BuildingEntity.class);
        buildingEntity.getRentareas().clear();// xóa hết dữ liệu list rentareas trong buildingEntity
        saveBuilding(buildingEntity, building);

        return new ResponseDTO();
    }

    public void saveBuilding(BuildingEntity result, BuildingDTO building) {
//      CHUYEN DOI TU TYPECODE  dạng list<String> sang string
        List<String> type = building.getTypeCode();
        String typeCode = type.stream().map(i ->i.toString() ).collect(Collectors.joining(","));
        result.setTypeCode(typeCode);
//      chuyển đôi từ string rentarea sang các entity rentarea
        if(building.getRentArea() != null && !building.getRentArea().equals("")){
            String [] values = building.getRentArea().trim().split(",");
            for(String v : values){

                RentAreaEntity rentAreaEntity = new RentAreaEntity();
                rentAreaEntity.setValue( v);
                rentAreaEntity.setBuilding(result);
                result.getRentareas().add(rentAreaEntity);// them cac dữ liệu mới của list rentareas vào buildingEntity

            }
        }

        buildingRepository.save(result);

    }

    @Override
    @Transactional
    public ResponseDTO deleteBuilding(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            ResponseDTO response = new ResponseDTO();
            response.setMessage("No building ids provided for deletion");
            return response;
        }
        buildingRepository.deleteByIdIn(ids);
        ResponseDTO response = new ResponseDTO();
        response.setMessage("Buildings deleted successfully");
        return response;
    }

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
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest2 request, int pageNo, int pageSize) {
        // Tạo đối tượng Pageable với chỉ số trang bắt đầu từ 0 (trừ đi 1 từ pageNo)
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        
        // Gọi repository với đối tượng pageable để lấy dữ liệu đã phân trang
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(request, pageable);
        List<BuildingSearchResponse> result = new ArrayList<>();
    
        // Chuyển đổi các đối tượng entity thành response DTO
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

        if(ab.getStaffs() != null && !ab.getStaffs().isEmpty() && !ab.getStaffs().equals("")) {

            List<UserEntity> users = userRepository.findAllByIdIn(ab.getStaffs());
            building.setStaffsAssBuilding(users);// dong này se xóa hết nhân viên cũ và thêm danh sách nhân viên mới trong users
//            building.getStaffsAssBuilding().addAll(users); // dong code nay se them cac nhan vien mới trong users vao building và giữ nguyên các nhân viên cũ

        }
        buildingRepository.save(building);

        return new ResponseDTO();
    }
//    public void addRentArea(String rentArea, BuildingEntity building) {
//
//        if(rentArea != null && !rentArea.equals("")){
//            String [] values = rentArea.trim().split(",");
//            for(String v : values){
//
//                RentAreaEntity rentAreaEntity = new RentAreaEntity();
//                rentAreaEntity.setValue( v);
//                rentAreaEntity.setBuilding(building);
//                building.getRentareas().add(rentAreaEntity);
//
//            }
//        }
//    }

//    @Override
//    public List<BuildingSearchResponse> findAll2(BuildingSearchRequest2 request2, int pageNo, int pageSize) {
//
//        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
//
//        Page<BuildingEntity> buildingEntities =buildingRepository.findAll(pageable);
//        List<BuildingSearchResponse> result = new ArrayList<>();
//
//        for(BuildingEntity item: buildingEntities)
//        {
//            BuildingSearchResponse building = buildingResponseConverter.toBuildingSearchResponse(item);
//            result.add(building);
//        }
//        return result ;
//    }
}