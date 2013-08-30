package com.learning.util;

/**
 *@author zhengzh
 *@create 2013-8-30
 **/
public abstract class StringUtil {

	/**
	 * parentLevel format is "1.1", and maxChildLevel is "1.1.2"
	 * then getChildLevel return "1.1.3"
	 * if maxChildLevel is "" or null ,then getChildLevel return "1.1.1"
	 * @param parentLevel
	 * @param maxChildLevel
	 * @return
	 */
	public static String getChildLevel(String parentLevel,String maxChildLevel){
		String level = "";
		String[] levelStr = null ;
		if(maxChildLevel == null || "".equals(maxChildLevel)) {
			level = new StringBuilder(parentLevel).append(".1").toString();
		}else {
			levelStr = maxChildLevel.split("\\.");
			Integer num = Integer.parseInt(levelStr[levelStr.length-1]) + 1;
			levelStr[levelStr.length-1] = num.toString() ;
			StringBuilder sb = new StringBuilder();
			for(String str : levelStr ) {
				sb.append(str).append(".");
			}
			level = sb.substring(0, sb.length()-1);
		}
		return level;
	}
	
	public static void main(String[] args) {
		String level =null ;
		level = getChildLevel("1.1", "");
		System.out.println("" + level);
		level = getChildLevel("1.1", "1.1.2");
		System.out.println("" + level);
	}
}
