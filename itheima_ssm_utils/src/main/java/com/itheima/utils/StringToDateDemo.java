package com.itheima.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/*
    自己手动创建一个工具来，来实现String转换成Date类
    解决的是：SpringMVC不能实现这种2000-10-10格式的类型转换
    Converter<S,T>:
        S:指代是我们获取的参数类型
        T：我们需要转换成的类型
 */
public class StringToDateDemo implements Converter<String,Date>{//1
    @Override
    public Date convert(String source) {//source接收页面的参数数据
        //当source为空时则不能进入日期类型转换
        if(source==null){
            throw new RuntimeException("接收的参数值为null");
        }
        //类型转换
        Date date=null;
        DateFormat dateFormat=getSimpleDateFormat(source);
        try {
            date= dateFormat.parse(source);
            return date;
        } catch (Exception e) {
            throw new RuntimeException("类型转换失败");
        }
    }

    private SimpleDateFormat getSimpleDateFormat(String source){
        SimpleDateFormat sdf = null;

        if(Pattern.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$", source)){
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        }else if(Pattern.matches("^\\d{4}\\d{2}\\d{2} \\d{2}:\\d{2}$", source)){
            sdf = new SimpleDateFormat("yyyyMMdd");
        }else if(Pattern.matches("^\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}$", source)){
            sdf = new SimpleDateFormat("yyyy/MM/dd");
        }

        return sdf;
    }
}
