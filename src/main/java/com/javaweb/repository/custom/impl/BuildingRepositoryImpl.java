package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingSearchRequest2;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.JpaCountQueryCreator;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<BuildingEntity> findAll(BuildingSearchRequest2 request, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT b.* FROM building b ");
        StringBuilder where = new StringBuilder(" WHERE 1=1 ");
        queryNomal(request,where);
        querySpecial(request,where);
        sql.append(where);
        sql.append(" GROUP BY b.id");
        
        // Thêm phân trang
        if (pageable != null) {
            sql.append(" LIMIT ").append(pageable.getPageSize());
            sql.append(" OFFSET ").append(pageable.getOffset());
        }
        
        System.out.println(sql);
        
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }
    public static void joinTable( BuildingSearchRequest2 request, StringBuilder sql)
    {
    }
    public static void queryNomal(BuildingSearchRequest2 request, StringBuilder where)  {
        try {
            Field[] Fields = BuildingSearchRequest2.class.getDeclaredFields();
            for(Field item: Fields)
            {
                item.setAccessible(true);
                String fieldname = item.getName();
                if(!fieldname.equals("staffId") && !fieldname.equals("typeCode") && !fieldname.startsWith("area") &&
                        !fieldname.startsWith("rentPrice"))
                {
                    Object value = item.get(request);
                    if(value != null && !value.toString().equals("") && !value.toString().equals("null"))
                    {
                        if(item.getType().getName().equals("java.lang.Long")
                                || item.getType().getName().equals("java.lang.Integer")
                                || item.getType().getName().equals("java.lang.Float"))
                        {
                            where.append(" AND b."+fieldname+" = "+value+ " ");
                        }
                        else if(item.getType().getName().equals("java.lang.String"))
                        {
                            where.append(" AND b."+fieldname+" LIKE '%"+value+ "%' ");
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }


    public static void querySpecial(BuildingSearchRequest2 request, StringBuilder where)
    {
        Long staffid = request.getStaffId();
        if(staffid != null && staffid != 0)
        {
            where.append(" and exists (select * from assignmentbuilding ab where ab.buildingid = b.id and ab.staffid = "+staffid+") ");
        }
        Long renPriceFrom = request.getRentPriceFrom();
        Long rentPriceTo = request.getRentPriceTo();
        if(renPriceFrom != null && renPriceFrom != 0 || rentPriceTo != null && rentPriceTo != 0)
        {
            if(renPriceFrom != null && renPriceFrom != 0)
                {
                    where.append(" and b.rentprice >= "+renPriceFrom+" ");
                }
            if(rentPriceTo != null && rentPriceTo != 0)
                {
                    where.append(" and b.rentprice <= "+rentPriceTo+" ");
                }
        }
        Long areaTo = request.getAreaTo();
        Long areaFrom = request.getAreaFrom();
        if(areaTo != null && areaTo != 0 || areaFrom != null && areaFrom != 0)
        {
            where.append(" and exists (select * from rentarea ra where ra.buildingid = b.id  ");
            if(areaFrom != null && areaFrom != 0)
                {
                    where.append(" and ra.value >= "+areaFrom+" ");
                }
            if(areaTo != null && areaTo != 0)
                {
                    where.append(" and ra.value <= "+areaTo+" ");

                }
            where.append(" ) ");
        }

        List<String > typeCode = request.getTypeCode();
        if(typeCode!=null && typeCode.size() !=0)
        {
            where.append(" and ( ");
            String typeCodeStr = typeCode.stream().map(i ->" b.type like '%"+i.toString()+"%' ").collect(Collectors.joining(" or "));
            where.append(typeCodeStr +" ) ");
        }

    }
}
