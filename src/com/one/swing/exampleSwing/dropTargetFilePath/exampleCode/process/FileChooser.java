package com.one.swing.exampleSwing.dropTargetFilePath.exampleCode.process;

import com.one.swing.exampleSwing.dropTargetFilePath.exampleCode.po.FileItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * @author  liaoyu
 * @created Apr 28, 2015
 */
/**
 * Java按位置解析文本文件(使用Swing选择文件)
 工作中遇到这样的一个需求，按位置解析一些文本文件，它们由头部、详情、尾部组成，并且每一行的长度可能不一样，每一行代表的意思也可能不一样，但是每一行各个位置代表的含义已经确定了。

 4.如果需要添加新的要处理文件类型，往file_list_config.properties文件中进行追加，编写各个字段解析规则即可。
 * */
public class FileChooser implements ActionListener {

    private JFrame frame = new JFrame("Parse External File");
    private JTabbedPane panel = new JTabbedPane();
    private Container container = new Container();
    private JLabel label = new JLabel("File Path : ");
    private JTextField text = new JTextField();
    private JButton chooseBtn = new JButton("Choose");
    private JFileChooser jfc = new JFileChooser();
    private JButton generateBtn = new JButton("Parse");
    private JLabel fileTypeLabel = new JLabel("Extfile Name:");
    private JComboBox<FileItem> jSelect = new JComboBox<FileItem>();     // initial JComboBox
    private List<FileItem> fileList = new ArrayList<FileItem>();

    private File file = null;

    public FileChooser() {
        jfc.setCurrentDirectory(new File("d://"));
        double lx = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double ly = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        frame.setLocation(new Point((int) (lx / 2) - 150, (int) (ly / 2) - 150));
        frame.setSize(400, 260);
        frame.setLayout(null);
        //填充下拉框
        fileList = ResourceFactory.getSington().getFileItemList();
        for (FileItem item : fileList) {
            jSelect.addItem(item);
        }

        fileTypeLabel.setBounds(10, 20, 90, 30);
        jSelect.setBounds(100, 20, 250, 30);
        label.setBounds(10, 75, 90, 30);
        text.setBounds(100, 75, 150, 30);
        chooseBtn.setBounds(270, 75, 80, 30);
        generateBtn.setBounds(10, 120, 80, 30);

        jSelect.addActionListener(this);
        chooseBtn.addActionListener(this);
        generateBtn.addActionListener(this);

        container.add(fileTypeLabel);
        container.add(jSelect);
        container.add(label);
        container.add(text);
        container.add(chooseBtn);
        container.add(generateBtn);

        panel.add(container);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        new FileChooser();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(chooseBtn)) {
            jfc.setFileSelectionMode(0);
            int state = jfc.showOpenDialog(null);
            if (state == 1) {
                return;
            } else {
                file = jfc.getSelectedFile();
                text.setText(file.getAbsolutePath());
            }
        }

        if (e.getSource().equals(generateBtn)) {

            FileItem fileItem = (FileItem) jSelect.getSelectedItem();
            String targetFileName = fileItem.getKey() + ".properties";

            if (file == null) {
                JOptionPane.showMessageDialog(null, "Please choose a file to process.");
            } else {
                GenerateHtml.process(file, targetFileName);
                JOptionPane.showMessageDialog(null, "Generate html file successfully.");
            }

        }

    }

}


/**
 * 每一行的前两位决定了这一行各个位置代表的含义，例如以H1开关的第3位到第10位代表日期，尽管可以按照文档一行一行的对照来了解它们的含义，但这样不是一种折磨？
 * 经过一个小工具处理后，输出HTML文件，用浏览器打开后，展示如下：
 *   Flag   Date       Type
 *   H1    20150428   0222
 *
 *   要实现的几个功能 :

 可通过下拉框选择不同类型的文件
 使用Swing选择文件再进行处理
 易于扩展(可通过配置文件添加新的文件类型，而不需要更改Java代码)

 * */