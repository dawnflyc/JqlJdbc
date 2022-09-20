package com.dawnflyc.jql.impl;

import com.dawnflyc.jqlapi.sql.IJqlImpl;
import com.dawnflyc.jqlapi.sql.IPreParamManageFactory;
import com.dawnflyc.jqlapi.sql.ISqlHandle;
import com.dawnflyc.jqljdbc.JdbcSqlHandle;
import com.dawnflyc.jqljdbc.PreParamManage;

/**
 * jql 实现
 */
public class JqlJdbcImpl implements IJqlImpl {
    @Override
    public ISqlHandle getSqlHandle() {
        return new JdbcSqlHandle();
    }

    @Override
    public IPreParamManageFactory getPreParamManageFactory() {
        return PreParamManage::new;
    }
}
