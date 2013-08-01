 // 定义一个一周七天的枚举类型
 package com.learning.java.enum1; 

 public enum WeekDayEnum { 
    Mon(1), Tue(2), Wed(3), Thu(4), Fri(5), Sat(6), Sun(7); 

    private int index; 

    WeekDayEnum(int idx) { 
        this.index = idx; 
    } 

    public int getIndex() { 
        return index; 
    } 
 }