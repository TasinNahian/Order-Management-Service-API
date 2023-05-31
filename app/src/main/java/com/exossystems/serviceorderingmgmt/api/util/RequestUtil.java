package com.exossystems.serviceorderingmgmt.api.util;

import java.util.Map;

public class RequestUtil {
    private static final String LIMIT = "limit";
    private static final String OFFSET = "offset";

    public static int[] validateRequestParams(Map<String, Object> requestParams){
        if(!requestParams.containsKey(LIMIT)){
            requestParams.put(LIMIT, 20);
        }
        if(!requestParams.containsKey(OFFSET)){
            requestParams.put(OFFSET, 0);
        }
        Integer limit = null;
        Integer offset = null;
        try{
            limit = Integer.valueOf(requestParams.get(LIMIT).toString());
            offset = Integer.valueOf(requestParams.get(OFFSET).toString());

        }catch (Exception e){
            //todo will create customised exception
            throw new RuntimeException("Please provide valid limit or offset");
        }
        if(limit < 0 || limit > 25){
            //todo will create customised exception
            throw new RuntimeException("Invalid limit value, valid values between 1-25");
        }
        if(offset < 0){
            //todo will create customised exception
            throw new RuntimeException("Invalid offset value");
        }
        requestParams.remove(LIMIT);
        requestParams.remove(OFFSET);
        return new int[]{limit, offset};
    }
}
