package com.cv.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;

import java.io.*;
import java.util.List;

public class CountryUtils {
    public static String getCountryCN(String iso_code)
    {
        switch (iso_code){
            case "ABW":      return "阿鲁巴岛";
            case "AFG":      return "阿富汗";
            case "AGO":      return "安哥拉";
            case "AIA":      return "安圭拉";
            case "ALA":      return "奥兰群岛";
            case "ALB":      return "阿尔巴尼亚";
            case "AND":      return "安道尔";
            case "ANT":      return "荷属安地列斯群岛";
            case "ARE":      return "阿联酋";
            case "ARG":      return "阿根廷";
            case "ARM":      return "亚美尼亚";
            case "ASC":      return "阿森松岛";
            case "ASM":      return "美属萨摩亚";
            case "ATA":      return "南极洲";
            case "ATF":      return "法属南部领地";
            case "ATG":      return "安提瓜和巴布达";
            case "AUS":      return "澳大利亚";
            case "AUT":      return "奥地利";
            case "AZE":      return "阿塞拜疆";
            case "BDI":      return "布隆迪";
            case "BEL":      return "比利时";
            case "BEN":      return "贝宁";
            case "BFA":      return "布基纳法索";
            case "BGD":      return "孟加拉";
            case "BGR":      return "保加利亚";
            case "BHR":      return "巴林";
            case "BHS":      return "巴哈马";
            case "BIH":      return "波斯尼亚和黑塞哥维那";
            case "BLR":      return "白俄罗斯";
            case "BLZ":      return "伯利兹";
            case "BMU":      return "百慕大";
            case "BOL":      return "玻利维亚";
            case "BRA":      return "巴西";
            case "BRB":      return "巴巴多斯";
            case "BRN":      return "文莱";
            case "BTN":      return "不丹";
            case "BVT":      return "布韦岛";
            case "BWA":      return "博茨瓦纳";
            case "CAF":      return "中非共和国";
            case "CAN":      return "加拿大";
            case "CCK":      return "科科斯群岛";
            case "CHE":      return "瑞士";
            case "CHL":      return "智利";
            case "CHN":      return "中国";
            case "CIV":      return "科特迪瓦";
            case "CMR":      return "喀麦隆";
            case "COD":      return "刚果（金）";
            case "COG":      return "刚果（布）";
            case "COK":      return "库克群岛";
            case "COL":      return "哥伦比亚";
            case "COM":      return "科摩罗";
            case "CPV":      return "佛得角";
            case "CRI":      return "哥斯达黎加";
            case "CUB":      return "古巴";
            case "CXR":      return "圣诞岛";
            case "CYM":      return "开曼群岛";
            case "CYP":      return "塞浦路斯";
            case "CZE":      return "捷克";
            case "DEU":      return "德国";
            case "DJI":      return "吉布提";
            case "DMA":      return "多米尼克";
            case "DNK":      return "丹麦";
            case "DOM":      return "多米尼加";
            case "DZA":      return "阿尔及利亚";
            case "ECU":      return "厄瓜多尔";
            case "EGY":      return "埃及";
            case "ERI":      return "厄立特里亚";
            case "ESP":      return "西班牙";
            case "EST":      return "爱沙尼亚";
            case "ETH":      return "埃塞俄比亚";
            case "FIN":      return "芬兰";
            case "FJI":      return "斐济";
            case "FLK":      return "弗兰克群岛";
            case "FRA":      return "法国";
            case "FRO":      return "法罗群岛";
            case "FSM":      return "密克罗尼西亚联邦";
            case "GAB":      return "加蓬";
            case "GBR":      return "英国";
            case "GEO":      return "格鲁吉亚";
            case "GGY":      return "格恩西岛";
            case "GHA":      return "加纳";
            case "GIB":      return "直布罗陀";
            case "GIN":      return "几内亚";
            case "GLP":      return "瓜德罗普";
            case "GMB":      return "冈比亚";
            case "GNB":      return "几内亚比绍";
            case "GRC":      return "希腊";
            case "GRD":      return "格林纳达";
            case "GRL":      return "格陵兰";
            case "GTM":      return "危地马拉";
            case "GUF":      return "法属圭亚那";
            case "GUM":      return "关岛";
            case "GUY":      return "圭亚那";
            case "HMD":      return "赫德和麦克唐纳群岛";
            case "HND":      return "洪都拉斯";
            case "HRV":      return "克罗地亚";
            case "HTI":      return "海地";
            case "HUN":      return "匈牙利";
            case "IDN":      return "印度尼西亚";
            case "IMN":      return "曼岛";
            case "IND":      return "印度";
            case "IOT":      return "英属印度洋领地";
            case "IRL":      return "爱尔兰";
            case "IRN":      return "伊朗";
            case "IRQ":      return "伊拉克";
            case "ISL":      return "冰岛";
            case "ISR":      return "以色列";
            case "ITA":      return "意大利";
            case "JAM":      return "牙买加";
            case "JEY":      return "泽西岛";
            case "JOR":      return "约旦";
            case "JPN":      return "日本本土";
            case "KAZ":      return "哈萨克斯坦";
            case "KEN":      return "肯尼亚";
            case "KGZ":      return "吉尔吉斯斯坦";
            case "KHM":      return "柬埔寨";
            case "KIR":      return "基里巴斯";
            case "KNA":      return "圣其茨-尼维斯";
            case "KOR":      return "韩国";
            case "KWT":      return "科威特";
            case "LAO":      return "老挝";
            case "LBN":      return "黎巴嫩";
            case "LBR":      return "利比里亚";
            case "LBY":      return "利比亚";
            case "LCA":      return "圣卢西亚";
            case "LIE":      return "列支敦士登";
            case "LKA":      return "斯里兰卡";
            case "LSO":      return "莱索托";
            case "LTU":      return "立陶宛";
            case "LUX":      return "卢森堡";
            case "LVA":      return "拉脱维亚";
            case "MAR":      return "摩洛哥";
            case "MCO":      return "摩纳哥";
            case "MDA":      return "摩尔多瓦";
            case "MDG":      return "马达加斯加";
            case "MDV":      return "马尔代夫";
            case "MEX":      return "墨西哥";
            case "MHL":      return "马绍尔群岛";
            case "MKD":      return "马其顿";
            case "MLI":      return "马里";
            case "MLT":      return "马耳他";
            case "MMR":      return "缅甸";
            case "MNG":      return "蒙古";
            case "MNP":      return "北马里亚纳群岛";
            case "MOZ":      return "莫桑比克";
            case "MRT":      return "毛里塔尼亚";
            case "MSR":      return "蒙特塞拉特";
            case "MTQ":      return "马提尼克";
            case "MUS":      return "毛里求斯";
            case "MWI":      return "马拉维";
            case "MYS":      return "马来西亚";
            case "MYT":      return "马约特岛";
            case "NAM":      return "纳米比亚";
            case "NCL":      return "新喀里多尼亚";
            case "NER":      return "尼日尔";
            case "NFK":      return "诺福克";
            case "NGA":      return "尼日利亚";
            case "NIC":      return "尼加拉瓜";
            case "NIU":      return "纽埃";
            case "NLD":      return "荷兰";
            case "NOR":      return "挪威";
            case "NPL":      return "尼泊尔";
            case "NRU":      return "瑙鲁";
            case "NZL":      return "新西兰";
            case "OMN":      return "阿曼";
            case "PAK":      return "巴基斯坦";
            case "PAN":      return "巴拿马";
            case "PCN":      return "皮特凯恩";
            case "PER":      return "秘鲁";
            case "PHL":      return "菲律宾";
            case "PLW":      return "帕劳";
            case "PNG":      return "巴布亚新几内亚";
            case "POL":      return "波兰";
            case "PRI":      return "波多黎各";
            case "PRK":      return "朝鲜";
            case "PRT":      return "葡萄牙";
            case "PRY":      return "巴拉圭";
            case "PSE":      return "巴勒斯坦";
            case "PYF":      return "法属玻利尼西亚";
            case "QAT":      return "卡塔尔";
            case "REU":      return "留尼汪";
            case "ROU":      return "罗马尼亚";
            case "RUS":      return "俄罗斯";
            case "RWA":      return "卢旺达";
            case "SAU":      return "沙特阿拉伯";
            case "SCG":      return "塞尔维亚,黑山";
            case "SDN":      return "苏丹";
            case "SEN":      return "塞内加尔";
            case "SGP":      return "新加坡";
            case "SGS":      return "南乔治亚和南桑德威奇群岛";
            case "SHN":      return "圣赫勒拿";
            case "SJM":      return "斯瓦尔巴和扬马廷";
            case "SLB":      return "所罗门群岛";
            case "SLE":      return "塞拉利昂";
            case "SLV":      return "萨尔瓦多";
            case "SMR":      return "圣马力诺";
            case "SOM":      return "索马里";
            case "SPM":      return "圣皮埃尔和密克隆";
            case "STP":      return "圣多美和普林西比";
            case "SUR":      return "苏里南";
            case "SVK":      return "斯洛伐克";
            case "SVN":      return "斯洛文尼亚";
            case "SWE":      return "瑞典";
            case "SWZ":      return "斯威士兰";
            case "SYC":      return "塞舌尔";
            case "SYR":      return "叙利亚";
            case "TAA":      return "特里斯坦达昆哈";
            case "TCA":      return "特克斯和凯克特斯群岛";
            case "TCD":      return "乍得";
            case "TGO":      return "多哥";
            case "THA":      return "泰国";
            case "TJK":      return "塔吉克斯坦";
            case "TKL":      return "托克劳";
            case "TKM":      return "土库曼斯坦";
            case "TLS":      return "东帝汶";
            case "TON":      return "汤加";
            case "TTO":      return "特立尼达和多巴哥";
            case "TUN":      return "突尼斯";
            case "TUR":      return "土耳其";
            case "TUV":      return "图瓦卢";
            case "TZA":      return "坦桑尼亚";
            case "UGA":      return "乌干达";
            case "UKR":      return "乌克兰";
            case "UMI":      return "美属外岛";
            case "URY":      return "乌拉圭";
            case "USA":      return "美国";
            case "UZB":      return "乌兹别克斯坦";
            case "VAT":      return "梵蒂冈城国";
            case "VCT":      return "圣文森特和格纳丁斯";
            case "VEN":      return "委内瑞拉";
            case "VGB":      return "维尔京群岛，英属";
            case "VIR":      return "维尔京群岛，美属";
            case "VNM":      return "越南";
            case "VUT":      return "瓦努阿图";
            case "WLF":      return "瓦利斯和福图纳";
            case "WSM":      return "萨摩亚";
            case "YEM":      return "也门";
            case "ZAF":      return "南非";
            case "ZMB":      return "赞比亚";
            case "ZWE":      return "津巴布韦";
            default:return "未知地区";
        }

    }

    public static void main(String[] args) throws IOException {
        String jsonStr = "";
        try {
            File jsonFile = new File("D:/xinguandata/country.json");
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(jsonStr);
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        List<JSONObject> records = JSON.parseArray(jsonObject.getString("RECORDS"), JSONObject.class);
        for(JSONObject jo:records)
        {
            System.out.println("case"+ " "+'\"'+jo.getString("code")+'\"'+":      "+
                    "return"+" "+'\"'+jo.getString("cname")+'\"'+";");
        }



    }


}

