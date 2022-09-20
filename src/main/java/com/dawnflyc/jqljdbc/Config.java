package com.dawnflyc.jqljdbc;

import java.io.IOException;
import java.util.Properties;

/**
 * 配置读取
 */
public class Config {

    private static Properties PROPERTIES =null;

    public static void load() {
        try {
            PROPERTIES =  new Properties();
            PROPERTIES.load(Config.class.getClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object get(String key) {
        if(PROPERTIES == null){
            load();
        }
        return PROPERTIES.get(key);
    }

    public static String getUrl(){
        return get("db.url").toString();
    }

    public static String getDriver(){
        return get("db.driver").toString();
    }

    public static String getUserName(){
        return get("db.username").toString();
    }

    public static String getPassword(){
        return get("db.password").toString();
    }


}
