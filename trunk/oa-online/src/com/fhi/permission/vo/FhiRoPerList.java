package com.fhi.permission.vo;

import java.util.ArrayList;

public class FhiRoPerList extends ArrayList {
    
    private Class itemClass;
    
    public FhiRoPerList(Class itemClass) {
        this.itemClass = itemClass;
    }
    
    public Object get(int index) {
        try {
            while (index >= size()) {
                add(itemClass.newInstance());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.get(index);
    }
}