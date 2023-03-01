package com.dawnflyc.jqljdbc;

import org.reflections.Reflections;

import java.util.HashSet;
import java.util.Set;

/**
 * sql结果处理器管理
 */
public class SqlResultHandleManage {

    private static final Set<ISqlResultHandle> sqlResultHandles = new HashSet<>();

    /**
     * 加载便扫描
     */
    static {
        Set<Class<? extends ISqlResultHandle>> subTypesOf = new Reflections("com.dawnflyc.jqljdbc").getSubTypesOf(ISqlResultHandle.class);
        for (Class<? extends ISqlResultHandle> aClass : subTypesOf) {
            try {
                sqlResultHandles.add(aClass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 获取支持该类型的结果处理器
     * @param type 类型 来自 java.sql.types
     * @return
     */
    public static ISqlResultHandle getResultHandle(int type){
        for (ISqlResultHandle sqlResultHandle : sqlResultHandles) {
            if(sqlResultHandle.isReceive(type)){
                return sqlResultHandle;
            }
        }
        return null;
    }
}
