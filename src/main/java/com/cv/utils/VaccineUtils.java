package com.cv.utils;

import javax.persistence.criteria.CriteriaBuilder;

public class VaccineUtils {
    public static double getVaccineNum(String province)
    {
        switch (province){
            case "辽宁":  return    40.90;
            case "黑龙江":  return     32.40;
            case "浙江":  return     38.80;
            case "安徽":  return     40.12;
            case "福建":  return     22.93;
            case "江西":  return     36.07;
            case "山东":  return     43.43;
            case "河南":  return     10.27;
            case "湖北":  return     47.55;
            case "湖南":  return     33.78;
            case "广东":  return     44.40;
            case "海南":  return     72.50;
            case "贵州":  return     32.32;
            case "云南":  return     97.40;
            case "陕西":  return     42.25;
            case "甘肃":  return     66.19;
            case "青海":  return     39.67;
            case "台湾":  return     11.45;
            case "北京":  return     84.26;
            case "上海":  return     77.60;
            case "天津":  return     85.00;
            case "重庆":  return     47.80;
            case "香港":  return     37.40;
            case "澳门":  return     25.93;
            case "广西":  return     34.46;
            case "宁夏":  return     38.60;
            case "西藏":  return     59.21;
            default:return -1.0;
        }
    }
}
