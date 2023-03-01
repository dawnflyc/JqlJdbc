package com.dawnflyc.jqljdbc;

import com.dawnflyc.jqlapi.sql.ISqlHandle;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * sqlhandle 实现
 */
public class JdbcSqlHandle implements ISqlHandle {
    @Override
    public List<Map<String, Object>> select(String sql, Map<String, Object> params) {
        try (JdbcManage jdbcManage = new JdbcManage()){
            try (PreparedStatement preparedStatement = jdbcManage.getConnection().prepareStatement(sql)) {
                fillPreparedStatement(preparedStatement, params);
                ResultSet resultSet = preparedStatement.executeQuery();
                List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
                while (resultSet.next()) {
                    Map<String, Object> row = new HashMap<String, Object>();
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    for (int i = 0; i < metaData.getColumnCount(); i++) {
                        ISqlResultHandle resultHandle = SqlResultHandleManage.getResultHandle(metaData.getColumnType(i + 1));
                        Object value = resultSet.getObject(i + 1);
                        if(resultHandle!=null){
                            value = resultHandle.handle(value);
                        }
                        row.put(metaData.getColumnLabel(i+1),value);
                    }
                    result.add(row);
                }
                return result;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object insert(String sql, Map<String, Object> params) {
        try (JdbcManage jdbcManage = new JdbcManage()){
            try(PreparedStatement preparedStatement = jdbcManage.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                fillPreparedStatement(preparedStatement, params);
                preparedStatement.executeUpdate();
                ResultSet id = preparedStatement.getGeneratedKeys();
                id.next();
                return id.getObject(1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(String sql, Map<String, Object> params) {
        try (JdbcManage jdbcManage = new JdbcManage()){
            try(PreparedStatement preparedStatement = jdbcManage.getConnection().prepareStatement(sql)){
                fillPreparedStatement(preparedStatement, params);
                return preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(String sql, Map<String, Object> params) {
        try (JdbcManage jdbcManage = new JdbcManage()){
            try(PreparedStatement preparedStatement = jdbcManage.getConnection().prepareStatement(sql)){
                fillPreparedStatement(preparedStatement, params);
                return preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void fillPreparedStatement(PreparedStatement preparedStatement,Map<String, Object> params) throws SQLException {
        for (String key : params.keySet()) {
            preparedStatement.setObject(Integer.parseInt(key),params.get(key));
        }
    }
}
