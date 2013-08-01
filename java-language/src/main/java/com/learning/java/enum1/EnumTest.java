package com.learning.java.enum1;

import java.util.Calendar;

import com.learning.util.Assert;

/**
 *@author zhengzh
 *@create 2013-7-30
 **/
public class EnumTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new EnumTest().switchDay();
	}
	
	 public void switchDay(){
		 // 读取当天的信息
		 WeekDayEnum today = readToday();
		 Assert.notNull(today, "对象不能为空。");
		 // 根据日期来选择进行活动
		 switch(today) {
			 case Mon:  System.out.println("today is mon"); break; 
			 case Tue:  System.out.println("today is tue"); break; 
			 case Wed:  System.out.println("today is wed"); break; 
			 case Thu:  System.out.println("today is thu"); break; 
			 case Fri: System.out.println("today is fri") ; break; 
			 case Sat:  System.out.println("today is sat"); break; 
			 case Sun:  System.out.println("today is sun"); break; 
		 }
	 }

	private WeekDayEnum readToday() {
		WeekDayEnum day = null ;
		//see Calendar.DAY_OF_WEEK
		int i = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		//这里把数字转换成枚举星期
		switch (i) {
			case 1 : day= WeekDayEnum.Sun; break;
			case 2 : day= WeekDayEnum.Mon; break;
			case 3 : day= WeekDayEnum.Tue; break;
			case 4 : day= WeekDayEnum.Wed; break;
			case 5 : day= WeekDayEnum.Thu; break;
			case 6 : day= WeekDayEnum.Fri; break;
			case 7 : day= WeekDayEnum.Sat; break;
		}
		return day;
	}

}
