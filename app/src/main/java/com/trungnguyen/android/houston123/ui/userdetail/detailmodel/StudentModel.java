package com.trungnguyen.android.houston123.ui.userdetail.detailmodel;

import com.trungnguyen.android.houston123.base.BaseModel;
import com.trungnguyen.android.houston123.base.BaseUserModel;
import com.trungnguyen.android.houston123.util.ModelResourceLoader;

import java.util.Arrays;
import java.util.List;

/**
 * Created by trungnd4 on 08/10/2018.
 */
public class StudentModel extends BaseUserModel {
    private String img;
    private String clazz;
    private String birthday;
    private String income;
    private String startDate;
    private String school;
    private String outDate;
    private String outReason;
    private String nameNT1;
    private String phoneNT1;
    private String carrerNT1;
    private String nameNT2;
    private String phoneNT2;
    private String carrerNT2;
    private String howToKnow;
    private String official;
    private String department;
    private String departmentName;


    public StudentModel(String name, String phoneNumber, String address, String userId) {
        super(name, phoneNumber, address, userId);
    }

    public StudentModel(String name, String phoneNumber, String address, String userId,
                        String clazz, String birthday, String income, String startDate, String school, String outDate, String outReason,
                        String nameNT1, String phoneNT1, String carrerNT1, String nameNT2, String phoneNT2, String carrerNT2, String howToKnow,
                        String official, String department, String departmentName) {
        super(name, phoneNumber, address, userId);
        this.clazz = clazz;
        this.birthday = birthday;
        this.income = income;
        this.startDate = startDate;
        this.school = school;
        this.outDate = outDate;
        this.outReason = outReason;
        this.nameNT1 = nameNT1;
        this.phoneNT1 = phoneNT1;
        this.carrerNT1 = carrerNT1;
        this.nameNT2 = nameNT2;
        this.phoneNT2 = phoneNT2;
        this.carrerNT2 = carrerNT2;
        this.howToKnow = howToKnow;
        this.official = official;
        this.department = department;
        this.departmentName = departmentName;
    }


    public StudentModel(String name, String phoneNumber, String address,
                        String clazz, String birthday, String income, String startDate, String school, String departmentName) {
        super(name, phoneNumber, address, "");
        this.clazz = clazz;
        this.birthday = birthday;
        this.income = income;
        this.startDate = startDate;
        this.school = school;
        this.departmentName = departmentName;
    }

    public StudentModel(String name, String phoneNumber, String address,
                        String clazz, String birthday, String income, String startDate, String school, String outDate, String outReason,
                        String nameNT1, String phoneNT1, String carrerNT1, String nameNT2, String phoneNT2, String carrerNT2, String howToKnow,
                        String official, String department, String departmentName) {
        super(name, phoneNumber, address, "");
        this.clazz = clazz;
        this.birthday = birthday;
        this.income = income;
        this.startDate = startDate;
        this.school = school;
        this.outDate = outDate;
        this.outReason = outReason;
        this.nameNT1 = nameNT1;
        this.phoneNT1 = phoneNT1;
        this.carrerNT1 = carrerNT1;
        this.nameNT2 = nameNT2;
        this.phoneNT2 = phoneNT2;
        this.carrerNT2 = carrerNT2;
        this.howToKnow = howToKnow;
        this.official = official;
        this.department = department;
        this.departmentName = departmentName;
    }

    public static BaseModel initFromResource(List<String> resources) {
        int position = 0;
        String name = resources.get(position++);
        String clazz = resources.get(position++);
        String sdt = resources.get(position++);
        String address = resources.get(position++);
        String birthday = resources.get(position++);
        String income = resources.get(position++);
        String startDate = resources.get(position++);
        String school = resources.get(position++);
        String outDate =resources.get(position++);
        String outReason = resources.get(position++);
        String nameNT1 = resources.get(position++);
        String phoneNT1 = resources.get(position++);
        String carrerNT1 = resources.get(position++);
        String nameNT2 = resources.get(position++);
        String phoneNT2 = resources.get(position++);
        String carrerNT2 = resources.get(position++);
        String toknow = resources.get(position++);
        String official = resources.get(position++);
        String depart = resources.get(position++);
        String departName = resources.get(position++);



        return new StudentModel(name, sdt, address,clazz,birthday,
                income, startDate, school, outDate, outReason, nameNT1, phoneNT1, carrerNT1, nameNT2, phoneNT2, carrerNT2, toknow, official, depart, departName);
    }

    public String getSafeObject(String object) {
        if (object == null) {
            return "";
        }
        return object;
    }

    public String getImg() {
        return img;
    }

    public String getClazz() {
        return clazz;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getIncome() {
        return income;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getSchool() {
        return school;
    }

    public String getOutDate() {
        return outDate;
    }

    public String getOutReason() {
        return outReason;
    }

    public String getNameNT1() {
        return nameNT1;
    }

    public String getPhoneNT1() {
        return phoneNT1;
    }

    public String getCarrerNT1() {
        return carrerNT1;
    }

    public String getNameNT2() {
        return nameNT2;
    }

    public String getPhoneNT2() {
        return phoneNT2;
    }

    public String getCarrerNT2() {
        return carrerNT2;
    }

    public String getHowToKnow() {
        return howToKnow;
    }

    public String getOfficial() {
        return official;
    }

    public String getDepartment() {
        return department;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    @Override
    public List<String> getSource() {
        return ModelResourceLoader.loadStudentResource();
    }

    @Override
    public List<String> getValue() {
        return Arrays.asList(getMainContent(),getSafeObject(clazz), getSubCotent(), getAddress(), getSafeObject(birthday),
                getSafeObject(income), getSafeObject(startDate), getSafeObject(school),
                getSafeObject(outDate),
                getSafeObject(outReason),
                getSafeObject(nameNT1),
                getSafeObject(phoneNT1),
                getSafeObject(carrerNT1),
                getSafeObject(nameNT2),
                getSafeObject(phoneNT2),
                getSafeObject(carrerNT2),
                getSafeObject(howToKnow),
                getSafeObject(official),
                getSafeObject(department),
                getSafeObject(departmentName));
    }
}
