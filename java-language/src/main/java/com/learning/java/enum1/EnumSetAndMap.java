package com.learning.java.enum1;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

/**
 *@author zhengzh
 *@create 2013-7-30
 **/
public class EnumSetAndMap {

	/**
	 * @param args
	 */
	// 定义一个 EnumMap 对象，映射表主键是日期枚举类型，值是颜色枚举类型
	private static Map<WeekDayEnum, RainbowColor> schema = 
			new EnumMap<WeekDayEnum, RainbowColor>(WeekDayEnum.class); 
	public static void main(String[] args) {
		   
		//show map example
		{
	    // 将一周的每一天与彩虹的某一种色彩映射起来
	    for (int i = 0; i < WeekDayEnum.values().length; i++) { 
	        schema.put(WeekDayEnum.values()[i], RainbowColor.values()[i]); 
	    } 
		 System.out.println("What is the lucky color today?"); 
		 System.out.println("It's " + schema.get(WeekDayEnum.Sun)); 
		}
		//show set example
		{
			 for(WeekDayEnum day : EnumSet.range(WeekDayEnum.Mon, WeekDayEnum.Fri)) { 
			     System.out.println(day); 
			 }
		}

	}

}
