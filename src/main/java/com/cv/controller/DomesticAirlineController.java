package com.cv.controller;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@RestController
public class DomesticAirlineController {
    @GetMapping("/domesticAirlineSearch")
    public JSONObject AirlineSearch(String startCity, String endCity, String date) throws IOException {
        String xml = Jsoup.connect(
                "http://www.webxml.com.cn/webservices/DomesticAirline.asmx/getDomesticAirlinesTime"
        ).data("startCity",startCity).data("lastCity",endCity).data("theDate",date)
                .data("userID","")
                .ignoreContentType(true)
                .execute()
                .body();
        // System.out.println(xml);

        FileWriter fwriter = null;
        try {
            fwriter = new FileWriter("../res.xml");
            fwriter.write(xml);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fwriter.flush();
                fwriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //2.创建DocumentBuilder对象
        JSONObject res = new JSONObject();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document d = builder.parse("../res.xml");
            NodeList sList = d.getElementsByTagName("AirlinesTime");
            //element(sList);
            List<JSONObject> list = SonDomDemo.node(sList);

            res.put("msg","success");
            res.put("data",list);

        } catch (Exception e) {
            e.printStackTrace();
            res.put("msg","没有查询到该航班");
        }
        return res;

    }
    static class SonDomDemo {
        //用Element方式
        public  void element(NodeList list){
            for (int i = 0; i <list.getLength() ; i++) {
                Element element = (Element) list.item(i);
                NodeList childNodes = element.getChildNodes();
                for (int j = 0; j <childNodes.getLength() ; j++) {
                    if (childNodes.item(j).getNodeType()== Node.ELEMENT_NODE) {
                        //获取节点
                        System.out.print(childNodes.item(j).getNodeName() + ":");
                        //获取节点值
                        System.out.println(childNodes.item(j).getFirstChild().getNodeValue());
                    }
                }
            }
        }

        public static List<JSONObject> node(NodeList list){
            List<JSONObject> res = new ArrayList<>();
            for (int i = 0; i <list.getLength() ; i++) {
                Node node = list.item(i);
                NodeList childNodes = node.getChildNodes();
                JSONObject airline = new JSONObject();
                for (int j = 0; j <childNodes.getLength() ; j++) {
                    if (childNodes.item(j).getNodeType()==Node.ELEMENT_NODE) {
                        //  System.out.print(childNodes.item(j).getNodeName() + ":");
                        //  System.out.println(childNodes.item(j).getFirstChild().getNodeValue());
                        airline.put(childNodes.item(j).getNodeName(),
                                childNodes.item(j).getFirstChild().getNodeValue());
                    }
                }
                res.add(airline);
            }
            return res;
        }

    }







}
