package com.money.money.utils;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.poi.word.WordUtil;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.*;

public class BeanUtil {

    /**
     * 复制数组
     */
    public static <T> List<T> copyList(List obj,Class<T>clazz){
        List<T> list = new ArrayList<>();
        obj.forEach(v->{
            Object res= null;
            try {
                res = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            BeanUtils.copyProperties(v,res);
            list.add((T)res);
        });
        return list;
    }
    public static <T> T copyProperties(Object source, Class<T> tClass, String... ignoreProperties) {
        T target = ReflectUtil.newInstanceIfPossible(tClass);
        BeanUtils.copyProperties(source, target, ignoreProperties);
        return target;
    }
    /**
     * 判空
     */
    public static boolean isEmpty(Object obj){
        if(obj==null){
            return true;
        }
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        } else if (obj instanceof List) {
            return ((List< ? >) obj).isEmpty();
        } else if (obj instanceof String) {
            return ((String) obj).length() == 0;
        }
        return false;
    }
    public static boolean isNotEmpty(Object obj){
        return !isEmpty(obj);
    }


    /***
     * 将实体类转换成map
     */
    public static Map<String, Object> toMap(Object obj)  {
        Class type = obj.getClass();
        Map<String, Object> returnMap = new HashMap<>();
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(type);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();

                if (!propertyName.equals("class")) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(obj);
                    if (result != null) {
                        if(descriptor.getPropertyType().equals(LocalDateTime.class)){
                            returnMap.put(propertyName, DateUtils.toString((LocalDateTime)result,DateUtils.date));
                        }else{
                            returnMap.put(propertyName, result);
                        }
                    } else {
                        returnMap.put(propertyName, "");
                    }
                }
            }
        } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return returnMap;
    }

    public static String getNumber(String num){
        if(BeanUtil.isEmpty(num)){
            num="0";
        }
        Integer number = Integer.parseInt(num);
        number++;
        StringBuilder numBuilder = new StringBuilder(number.toString());
        for (int i = numBuilder.length(); i < 4; i++) {
            numBuilder.insert(0, "0");
        }
        return numBuilder.toString();
    }

    /**
     * 根据长度生成随机数字
     * @param start 起始数字
     * @param end 结束数字
     * @return 生成的随机码
     */
    public static Integer randomCount(Integer start, Integer end){
        return (int)(Math.random()*(end - start +1) + start);
    }

    /**
     * 订单号生成
     * @param payType String 支付类型
     * @return 生成的随机码
     */
    public static String getOrderNo(String payType){
        return payType + randomCount(11111, 99999) + System.currentTimeMillis() + randomCount(11111, 99999);
    }

    /**
     * 是否匹配
     * @param str 源字段
     * @param target 待匹配字段
     * @param match 匹配度
     * @return 计算连续匹配字段进行匹配
     */
    public static boolean match(String str, String target,Integer match){
        if(match>10){
            match=10;
        }else if(match<0){
            match=0;
        }
        char[] a =target.toCharArray();
        int num = (int) Math.ceil(a.length*match/10);//匹配连续字符数（关键代码）
        int size = a.length-num+1;
        for (int i = 0; i < size; i++) {
            String ma ="";
            for (int j = i; j < i+num; j++) {
                ma+=a[j];
            }
            if(str.contains(ma)){
                return true;
            }
        }
        return false;
    }


    /**
     * 是否匹配
     * @param str 源字段
     * @param str1 待匹配字段
     * @param match 匹配度
     * @return 计算连续匹配字数进行匹配
     */
    public static boolean match1(String str, String str1,Integer match){
        str1 = str1.replaceAll("管理","")
                .replaceAll("平台","")
                .replaceAll("软件","")
                .replaceAll("系统","");
        if(match>10){
            match=10;
        }else if(match<0){
            match=0;
        }
        String[] all = str1.split(";");
        for (String target:all) {
            if(target.length()==0){
                continue;
            }
            List<CharIndex> m = new ArrayList<>();
            int num = 0;//匹配字数
            int maxLine = 0;//最大连续字数
            int line = 0;//当前连续字数
            for(int i = 0; i < target.length(); i++) {
                for (int j = 0; j < str.length(); j++) {
                    if(str.charAt(j)==target.charAt(i)){
                        m.add(new CharIndex(target.charAt(i),j));
                        num++;
                        if(num == 1){
                            line=1;
                            continue;
                        }
                        if(num>1&&m.get(num-2).getIndex()<=j) {
                            line++;
                        }else if (num > 1 && m.get(num-2).getIndex()>j) {
                            line=1;//连续字数复位
                        }
                        if(maxLine<line){
                            maxLine=line;
                        }
                    }
                }
            }
            if(maxLine>0&&(float)maxLine/target.length()>=(float)match/10){
                System.out.println(str1+"----【匹配标签："+target+"】 【匹配连续字数："+maxLine+"】 【匹配度："+(float)maxLine/target.length()+"】 【匹配参数："+(float)match/10+"】");
                return true;
            }
        }

        return false;
    }


    @Data
    public static class CharIndex{
        private char str;
        private int index;
        public CharIndex(char str,int index){
            this.str = str;
            this.index = index;
        }
    }

    /**
     * 计算字符串相似度
     * @param str
     * @param target
     * @return
     */
    public static float getSimilarityRatio(String str, String target) {

        int d[][]; // 矩阵
        int n = str.length();
        int m = target.length();
        int i; // 遍历str的
        int j; // 遍历target的
        char ch1; // str的
        char ch2; // target的
        int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
        if (n == 0 || m == 0) {
            return 0;
        }
        d = new int[n + 1][m + 1];
        for (i = 0; i <= n; i++) { // 初始化第一列
            d[i][0] = i;
        }

        for (j = 0; j <= m; j++) { // 初始化第一行
            d[0][j] = j;
        }

        for (i = 1; i <= n; i++) { // 遍历str
            ch1 = str.charAt(i - 1);
            // 去匹配target
            for (j = 1; j <= m; j++) {
                ch2 = target.charAt(j - 1);
                if (ch1 == ch2 || ch1 == ch2 + 32 || ch1 + 32 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                // 左边+1,上边+1, 左上角+temp取最小
                d[i][j] = Math.min(Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1), d[i - 1][j - 1] + temp);
            }
        }
        return (1 - (float) d[n][m] / Math.max(str.length(), target.length())) * 100F;
    }

    //java 8 乱序排序
    public static <T> Comparator<T> shuffle() {
        return (Comparator<T>) new ReverseComparator();
    }
    private static class ReverseComparator implements Comparator<Comparable<Object>>, Serializable {
        public int compare(Comparable<Object> c1, Comparable<Object> c2) {
            return new Random().nextBoolean()? 1 : -1;
        }
    }




}
