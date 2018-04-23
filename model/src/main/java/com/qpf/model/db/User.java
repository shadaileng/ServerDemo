package com.qpf.model.db;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String password;
    private String name;
    private String age;
    private String gender;
    private String email;
    private String heading;
    private String create_time;
    private String update_time;
    public User(){}
    public User(String id){ this.id = id;}
    public User(String password, String name, String age, String gender, String email, String heading, String create_time, String update_time) {
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.heading = heading;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getHeading() {
        return heading;
    }

    public String getCreate_time() {
        return create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", heading='" + heading + '\'' +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                '}';
    }

    public static List<String> getFields(){
        List<String> fields_ = new ArrayList<String>();
        Field[] fields = User.class.getDeclaredFields();
        for (Field field : fields){
            fields_.add(field.getName());
        }

        return fields_;
    }

    public static int getFieldsLength(){
        return User.class.getDeclaredFields().length;
    }

}
