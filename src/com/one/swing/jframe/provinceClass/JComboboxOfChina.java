package com.one.swing.jframe.provinceClass;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by vtstar on 2018/1/16.
 */
public class JComboboxOfChina {

    private JComboBox comboBox_provinc;
    private JComboBox comboBox_city;
    private JComboBox comboBox_area;

    private DefaultComboBoxModel modell = new DefaultComboBoxModel();
    private DefaultComboBoxModel model2 = new DefaultComboBoxModel();
    private DefaultComboBoxModel model3 = new DefaultComboBoxModel();

    public JComboboxOfChina(){
        // 设置省市区级三级联动数据
        //设置第一级数据，从xml 中获取数据
        for(String str: XMLDao._getProvinces()){
            modell.addElement(str);
        }
        comboBox_provinc = new JComboBox(modell);
        comboBox_provinc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox source = (JComboBox) e.getSource();
                //根据获取的省份 找到他下面的级别的市
                String provinces = (String) source.getSelectedItem(); // Object
                List<String> cities = XMLDao._getCities(provinces);
                model2.removeAllElements();
                for (String str:cities){
                    model2.addElement(str);
                }
                model3.removeAllElements();
                if(cities.size()>0){
                    for (String str:XMLDao._getDistricts(cities.get(0))){
                        model3.addElement(str);
                    }
                }
            }
        });
        //设置二级数据
        for (String str : XMLDao._getCities("北京市")) {
            model2.addElement(str);
        }
        comboBox_city = new JComboBox(model2);
        comboBox_city.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                JComboBox source = (JComboBox) evt.getSource();
                String city = (String) source.getSelectedItem();
                List<String> districts = XMLDao._getDistricts(city);
                model3.removeAllElements();
                for (String str : districts) {
                    model3.addElement(str);
                }
            }
        });
        //设置三级数据
        for (String str : XMLDao._getDistricts("北京市")) {
            model3.addElement(str);
        }
        comboBox_area = new JComboBox(model3);
    }

    public JComboBox getComboBox_provinc() {
        return comboBox_provinc;
    }

    public JComboBox getComboBox_city() {
        return comboBox_city;
    }

    public JComboBox getComboBox_area() {
        return comboBox_area;
    }

}
