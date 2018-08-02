package com.one.swing.jframe.provinceClass;

/**
 * Created by vtstar on 2018/1/16.
 */

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 解释xml ，添加了一个 jdom.jar
 * */
//解释 xml  的类
public class XMLDao {


    /**
     * 获取 xml  中 某一节点的 名称
     * @param express
     * @param source
     * @return
     * // 查找节点，并返回第一个符合条件节点
     * */
    public static Node _selectSingleNode(String express, Object source){
        Node result = null;
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();  // javax.xml.xpath.XPath;
        try {
            result = (Node) xPath.evaluate(express,source, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 根据xpath获取符合条件的所有节点
     * @param express
     * @param source
     * @return
     * // 查找节点，返回符合条件的节点集。
     * */
    public static NodeList _selectNodes(String express, Object source){
        NodeList result = null;
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        try {
            result = (NodeList) xPath.evaluate(express,source,XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 根据某个城市获取该 城市的所有地区
     * */
    public static List<String> _getDistricts(String districts){
        List<String> list = new ArrayList<String>();
        /*j
        ava中调用xml的方法：DocumentBuilderFactory
         **/
        //得到 DOM 解析器的工厂实例
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        //从 DOM 工厂获得 DOM 解析器
        try {
            documentBuilderFactory.setIgnoringElementContentWhitespace(true);

            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            // 把要解析的 XML 文档转化为 输入流 , 以便 DOM 解析器 解析它
            InputStream inputStream = new FileInputStream(new File("./src/com/one/swing/jframe/xmlProvince/Districts.xml"));
            //解析XML 文档的输入流，得到一个 Document       -- org.w3c.dom.Document;
            Document xmldoc = documentBuilder.parse(inputStream);
            //得到 xml 文档的 根节点
            Element rootElement = xmldoc.getDocumentElement();
            // 得到节点 的 子节点
            //NodeList nodeList = rootElement.getChildNodes();  // 例子https://www.cnblogs.com/be-come/p/5371458.html
            NodeList nodeList = _selectNodes("//District[@city='"+districts+"']",rootElement);
            for(int i=0;i<nodeList.getLength();i++){
                Node node = nodeList.item(i);
                String name = node.getTextContent();
                list.add(name);
                // （7）取得节点的属性值    例子：
                //String email=node.getAttributes().getNamedItem("email").getNodeValue();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 根据某个省份的名字获取省份的所有城市
     * */
    public static List<String> _getCities(String provinces){
        List<String> list = new ArrayList<String>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document xmldoc = documentBuilder.parse(new File("./src/com/one/swing/jframe/xmlProvince/Cities.xml"));
            Element root = xmldoc.getDocumentElement();
            NodeList nodeList = _selectNodes("//City[@Province='"+provinces+"']",root);
            for(int i=0;i<nodeList.getLength();i++){
                Node node = nodeList.item(i);
                String name = node.getTextContent();
                list.add(name);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    return  list;
    }

    /**
     * 获取所有省份
     * @return
     */
    public static List<String> _getProvinces() {
        List<String> list = new ArrayList<String>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            factory.setIgnoringElementContentWhitespace(true);

            DocumentBuilder db = factory.newDocumentBuilder();
            Document xmldoc = db.parse(new File("./src/com/one/swing/jframe/xmlProvince/Provinces.xml"));
            Element root = xmldoc.getDocumentElement();

            NodeList nodes = _selectNodes("/Provinces/Province", root);
            for(int i=0; i<nodes.getLength(); i++) {
                Node node = nodes.item(i);
                String name = node.getTextContent();
                list.add(name);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
