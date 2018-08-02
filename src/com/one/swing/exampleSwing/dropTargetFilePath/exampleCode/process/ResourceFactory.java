package com.one.swing.exampleSwing.dropTargetFilePath.exampleCode.process;

import com.one.swing.exampleSwing.dropTargetFilePath.exampleCode.po.FileItem;
import com.one.swing.exampleSwing.dropTargetFilePath.exampleCode.po.RecordField;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


/**
 * @author  liaoyu
 * @created Apr 28, 2015
 */
/**
 * 下面代码中 getFileItemList 是通过读取配置文件，返回一个 List 的结果集, 由于在 JComboBox 中展示的文本它会调用FileItem的toString进行输出，所以需要重写toString方法。
 * */

public class ResourceFactory {

    private static final String CONFIG_DIR_PATH = "./src/com/one/swing/exampleSwing/dropTargetFilePath/config/";
    private static final String CONFIG_EXTFILE_LIST_NAME = "file_list_config.properties";
    // 2.选择要处理的文件后，点击按钮 Parse，根据文件名称对缓存对象中获取各行的字段规则：
    //如何缓存没有该文本对应的规则，需解析对应的XML文件并放入缓存中。
    private Map<String, Map<String, List<RecordField>>> CONFIG_CACHE = new HashMap<String, Map<String, List<RecordField>>>();
    private List<FileItem> fileList = new ArrayList<FileItem>();
    private Document dom = null;
    private static ResourceFactory factory = null;

    private ResourceFactory() {
        initResources();
    }

    public static ResourceFactory getSington() {
        if (factory == null) {
            factory = new ResourceFactory();
        }
        return factory;
    }

    private void initResources() {

        try {
            InputStream inStream = new FileInputStream(CONFIG_DIR_PATH + CONFIG_EXTFILE_LIST_NAME);
            PropertyResourceBundle bundle = new PropertyResourceBundle(inStream);
            inStream.close();

            Enumeration<String> keys = bundle.getKeys();
            while (keys.hasMoreElements()) {
                FileItem fileItem = new FileItem();
                String key = keys.nextElement();
                fileItem.setKey(key);
                fileItem.setValue(bundle.getString(key));
                fileList.add(fileItem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseTargetExtfile(String targetFileName) {
        try {
            InputStream inStream = new FileInputStream(CONFIG_DIR_PATH + targetFileName);
            PropertyResourceBundle bundle = new PropertyResourceBundle(inStream);
            inStream.close();

            String indicator = "";
            String defName = "";
            Enumeration<String> keys = bundle.getKeys();
            Map<String, List<RecordField>> records = new HashMap<String, List<RecordField>>();

            while (keys.hasMoreElements()) {
                indicator = keys.nextElement();
                defName = bundle.getString(indicator);
                List<RecordField> fields = new ArrayList<RecordField>();

                InputStream tmpInStream = new FileInputStream(CONFIG_DIR_PATH + defName);
                parseXmlFile(tmpInStream);
                tmpInStream.close();

                NodeList nodeList = dom.getElementsByTagName("field");

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    RecordField field = new RecordField();

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        field.setTitle(element.getTextContent());
                        field.setLength(Integer.valueOf(element.getAttribute("length")));
                    }

                    fields.add(field);
                }

                records.put(indicator, fields);

            }

            CONFIG_CACHE.put(targetFileName, records);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void parseXmlFile(InputStream inStream) {
        // get the factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            // Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            // parse using builder to get DOM representation of the XML file
            dom = db.parse(inStream);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public Map<String, List<RecordField>> getConfigCache(String targetFileName) {
        if (CONFIG_CACHE.get(targetFileName) == null) {
            parseTargetExtfile(targetFileName);
        }
        return CONFIG_CACHE.get(targetFileName);
    }

    public List<FileItem> getFileItemList() {
        return fileList;
    }
}
