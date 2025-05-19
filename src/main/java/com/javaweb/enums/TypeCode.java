package com.javaweb.enums;

import javax.lang.model.element.TypeElement;
import java.util.HashMap;
import java.util.Map;

public enum TypeCode {
    NOI_THAT("Nội thất"),
    NGUYEN_CAN("Nguyên căn"),
    TANG_TRET("Tầng trệt");

    public final String TypeCodeName;
    TypeCode(String typeCodeName) {
        this.TypeCodeName = typeCodeName;
    }
    public static Map<String, String> type(){
        Map<String , String> typeCodes = new HashMap<>();
        for(TypeCode it : TypeCode.values()){
            typeCodes.put(it.toString(), it.TypeCodeName);
        }
        return typeCodes;
    }

}
