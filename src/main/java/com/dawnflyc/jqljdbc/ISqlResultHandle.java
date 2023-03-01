package com.dawnflyc.jqljdbc;

/**
 * sql结果处理器
 */
public interface ISqlResultHandle {
    /**
     * 是否接受某类型
     * @param type 类型 来自 java.sql.types
     * @return 是否接受
     */
    boolean isReceive(int type);

    /**
     * 处理接受的类型
     * @param value 值
     * @return 处理好的值
     */
    Object handle(Object value);

}
