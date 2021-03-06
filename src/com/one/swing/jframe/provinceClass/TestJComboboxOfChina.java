package com.one.swing.jframe.provinceClass;

import javax.swing.*;

/**
 * Created by vtstar on 2018/1/16.
 */
public class TestJComboboxOfChina {

    public static void main(String[] args) {
        JFrame j = new JFrame();
        j.setSize(300,300);
        j.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        j.setLayout(null);

        //构建中国各大城市的三级联动下拉框
        JComboboxOfChina box = new JComboboxOfChina();

        //构造省级下拉框
        JLabel label_privince = new JLabel("省份：");
        label_privince.setBounds(50, 50, 50, 30);
        JComboBox combobox_privince = box.getComboBox_provinc();
        combobox_privince.setBounds(100, 50, 150, 30);
        j.add(label_privince);
        j.add(combobox_privince);

        //构造市级下拉框
        JLabel label_city = new JLabel("城市：");
        label_city.setBounds(50, 100, 50, 30);
        JComboBox combobox_city = box.getComboBox_city();
        combobox_city.setBounds(100, 100, 150, 30);
        j.add(label_city);
        j.add(combobox_city);

        //构建区级下拉框
        JLabel label_area = new JLabel("地区：");
        label_area.setBounds(50, 150, 50, 30);
        JComboBox combobox_area = box.getComboBox_area();
        combobox_area.setBounds(100, 150, 150, 30);
        j.add(label_area);
        j.add(combobox_area);

        j.setVisible(true);
    }
}
