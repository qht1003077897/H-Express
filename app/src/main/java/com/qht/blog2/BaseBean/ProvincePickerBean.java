package com.qht.blog2.BaseBean;

import com.bigkoo.pickerview.model.IPickerViewData;
/**
 * Created by QHT on 2017-05-02.
 */

public class ProvincePickerBean implements IPickerViewData {
    private long id;
    private String name;
    private String description;
    private String others;

    public ProvincePickerBean(long id,String name,String description,String others){
        this.id = id;
        this.name = name;
        this.description = description;
        this.others = others;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    //这个用来显示在PickerView上面的字符串,PickerView会通过getPickerViewText方法获取字符串显示出来。
    @Override
    public String getPickerViewText() {
        return name;
    }
}