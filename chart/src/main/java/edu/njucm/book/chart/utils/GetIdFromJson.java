package edu.njucm.book.chart.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.formula.functions.T;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetIdFromJson {
    @SuppressWarnings("unchecked")
    public static Long get(String json){
        ObjectMapper mapper = new ObjectMapper();
        Long id=null;
        try{
            Map<String,Long> map = mapper.readValue(json, HashMap.class);
            id=(Long)map.get("id");
        }catch (IOException e){
            System.out.println("IOException");
            e.printStackTrace();
        }
        return id;
    }
}
