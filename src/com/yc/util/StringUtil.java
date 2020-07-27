package com.yc.util;

import java.util.Random;

public class StringUtil {
	//字符串非空判断
	public static boolean isNotNull(String str){
		if(null==str || "".equals(str)){
			return false;
		}
		
		return true;
	}
	
	//对象转字符串‘’
	public static String objectString(Object obj){
		if(null==obj){
			return null;
		}
		return obj.toString();
	}
	//随机数
	public static String genOrderId(){
		return  "b"+System.currentTimeMillis();
	}

}
