package com.base.service;

import com.base.model.AlarmSummary;
import com.base.model.Alarmeventdetail;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SuppressWarnings("unchecked")
public class BaseService extends SqlMapClientDaoSupport{
	
	public static SqlMapClient sqlMap = null;
	
	static{
		init();
	}
	
	/**
	 * 初始化：sqlMap和所有Monitor
	 */
	private static void init(){
		try {//sql执行客户端
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(Resources.getResourceAsReader("SqlMapCommonConfig.xml"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

    /**
     * 获取告警表分页集合
     * @param skip
     * @param max
     * @throws SQLException
     */
    public static List<Alarmeventdetail> selectPageAlarm(Integer skip, Integer max) throws SQLException {
        Map<String, Integer> map = new HashMap<>(2);
        map.put("skip", skip);
        map.put("max", max);
        return sqlMap.queryForList("selectPageAlarm", map);
    }

    /**
     * 获取告警表总条数
     * @return
     * @throws SQLException
     */
    public static Integer selectAlarmCount() throws SQLException{
        return (Integer)sqlMap.queryForObject("selectAllAlarmCount");
    }

    /**
     * 获取汇总表集合
     * @param alarmeventdetail
     * @return
     * @throws SQLException
     */
    public static List<AlarmSummary> selectAlarmSummary(Alarmeventdetail alarmeventdetail) throws SQLException{
        return sqlMap.queryForList("selectAlarmSummary", alarmeventdetail);
    }

    /**
     * 插入汇总记录
     * @param model
     * @throws SQLException
     */
    public static void insertAlarmSummary(AlarmSummary model) throws SQLException{
        sqlMap.insert("insertAlarmSummary", model);
    }

    /**
     * 更新汇总表总数
     * @param model
     * @throws SQLException
     */
    public static void updateAlarmSummaryTotal(AlarmSummary model) throws SQLException{
        sqlMap.update("updateAlarmSummaryTotal", model);
    }
}
