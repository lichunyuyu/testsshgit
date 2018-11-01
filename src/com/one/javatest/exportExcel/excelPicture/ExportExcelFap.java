package com.one.javatest.exportExcel.excelPicture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vtstar on 2018/10/29.
 */
public class ExportExcelFap {



    /**
     * 数据源
     * */
    public List<Map> selectDatas(){

        List<Map> mapList = new ArrayList<Map>();
        for(int j=0;j<4;j++){
            Map map = new HashMap();
            List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
            for(int i=0;i<5;i++){
                Map map1 = new HashMap();
                map1.put("index",i+1);
                map1.put("name","小明-"+i+"-j-"+j);
                map1.put("score",80+i);
                list.add(map1);
            }
            map.put("students",list);

            Map teacherMap = new HashMap();
            teacherMap.put("name","老师-"+j);

            map.put("teacher",teacherMap);

            mapList.add(map);
        }
        return mapList;
    }
}
