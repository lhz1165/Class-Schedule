package com.lhz.estest.course_enum;

/**
 * Created by: hz.lai
 * Date: 2021/6/16
 * Description:
 */
public enum CourseEnum {
    ONE(1, "8:00-8:45"),
    TWO(2, "8:55-9:40"),
    THREE(3, "10:55-11:40"),
    FOUR(4, "11:50-12:35"),
    FIVE(5, "14:00-14:45"),
    SIX(6, "14:55-15:40"),
    SEVEN(7, "16:00-16:45"),
    EIGHT(8, "16:55-17:40"),
    NINE(9, "17:50-18:35"),
    TEN(10, "19:20-20:05"),
    ELEVEN(11, "20:15-21:00"),
    TWELVE(12, "20:15-21:00"),
    THIRTEEN(13, "21:10-21:55");
    Integer courseNo;
    String timePeriod;

    CourseEnum(Integer courseNo, String timePeriod) {

        this.courseNo = courseNo;
        this.timePeriod = timePeriod;
    }

    public static CourseEnum getCourseEnumByTimePeriod(String timePeriod) {
        CourseEnum[] courseEnums = values();
        for (CourseEnum courseEnum : courseEnums) {
            if (courseEnum.timePeriod.equals(timePeriod)) {
                return courseEnum;
            }
        }
        return null;
    }

    public Integer getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(Integer courseNo) {
        this.courseNo = courseNo;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }
}
