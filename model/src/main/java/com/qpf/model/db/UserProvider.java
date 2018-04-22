package com.qpf.model.db;

import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserProvider {
    private Field[] fields ;
    private List<String> columns;
    private String realTableName;
    public UserProvider(){
        columns = new ArrayList<String>();
        fields = User.class.getDeclaredFields();
        for (Field field : fields){
            columns.add(field.getName().toUpperCase());
        }
        realTableName = "user";
    }

    public String selectByFullSql(Map<String, String> map){
        String sql = map.get("fullSQL");
        return sql;
    }

    public String selectUserById(Map<String, Object> map){
        SQL sql = new SQL();
        User user = (User) map.get(realTableName);
        sql.SELECT("*")
                .FROM(realTableName)
                .WHERE("ID = '" + user.getId() + "'");
        return sql.toString();
    }

    public String selectUserByBean(Map<String, Object> map){
        SQL sql = new SQL();
        User user = (User) map.get(realTableName);
        List<String> values = new ArrayList<String>();

        sql.SELECT("*")
                .FROM(realTableName);
        List<String> conditions = getConditionsByBean(user);
        for(int i = 0, l = conditions.size(); i < l; i++){
            if(i < l - 1) {
                sql.WHERE(conditions.get(i)).AND();
            } else {
                sql.WHERE(conditions.get(i));
            }
        }

        return sql.toString();
    }

    private List<String> getConditionsByBean(Object obj){
        List<String> conditions = new ArrayList<String>();

        Field[] fields_ = obj.getClass().getDeclaredFields();
        try {
            for (Field field : fields_){
                String name = field.getName();
                String getter = new StringBuffer("get").append(name.substring(0, 1).toUpperCase()).append(name.substring(1)).toString();

                Method method = User.class.getMethod(getter);
                Object invoke = method.invoke(obj);
                if(invoke != null){
                    conditions.add(name.toUpperCase() + " = '" + invoke + "'");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conditions;
    }

    public String selectUserByConditions(Map<String, Object> map){
        SQL sql = new SQL();
        Object paramWhere =  map.get("paramWhere");
        Object paramOrder = map.get("paramOrder");
        sql.SELECT("*")
                .FROM(realTableName);
        if(paramWhere != null){
            sql.WHERE((String) paramWhere);
        }
        if(paramOrder != null){
            for(String order : (String[])paramOrder) {
                sql.ORDER_BY(order);
            }
        }

        return sql.toString();
    }

    public String updateUserById(Map<String, Object> map){
        SQL sql = new SQL();
        Object user = map.get("user");

        sql.UPDATE(realTableName).WHERE("ID = '" + ((User)user).getId() + "'");
        List<String> sets = getConditionsByBean(user);
        for (String set : sets){
            sql.SET(set);
        }

        return sql.toString();
    }

    public String deleteUserById(Map<String, Object> map){
        SQL sql = new SQL();
        Object user = map.get(realTableName);

        sql.DELETE_FROM(realTableName).WHERE("ID = '" + ((User)user).getId() + "'");

        return sql.toString();
    }

    public String insertUser(Map<String, Object> map){
        SQL sql = new SQL();
        Object user = map.get(realTableName);
        sql.INSERT_INTO(realTableName);
        Map<String, String> map_ = getColumnValues(user);
        for(Map.Entry<String, String> entry : map_.entrySet()){
            sql.VALUES(entry.getKey(), entry.getValue());
        }
        return sql.toString();
    }

    private Map<String, String> getColumnValues(Object obj){
        Map<String, String> map = new HashMap<String, String>();

        Field[] fields_ = obj.getClass().getDeclaredFields();
        try {
            for (Field field : fields_){
                String name = field.getName();
                String getter = new StringBuffer("get").append(name.substring(0, 1).toUpperCase()).append(name.substring(1)).toString();

                Method method = User.class.getMethod(getter);
                Object invoke = method.invoke(obj);
                if(invoke != null){
                    map.put(name.toUpperCase(), "'" + invoke + "'");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

}
