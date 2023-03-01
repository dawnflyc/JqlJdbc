package com.dawnflyc.jqljdbc;

import com.dawnflyc.jqlapi.sql.IPreParamManage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 预编译管理实现
 */
public class PreParamManage implements IPreParamManage {

    private final List<Object> params = new ArrayList<Object>();

    @Override
    public String allocPreParam(Object o) {
        params.add(o);
        return "?";
    }

    @Override
    public Map<String, Object> toParams() {
        Map<String,Object> map = new HashMap<String,Object>();
        for (int i = 0; i < params.size(); i++) {
            map.put((i+1)+"", params.get(i));
        }
        return map;
    }

    @Override
    public void done() {
        ;
    }
}
