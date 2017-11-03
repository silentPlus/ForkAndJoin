package com.base.task;

import com.base.model.AlarmSummary;
import com.base.model.Alarmeventdetail;
import com.base.service.BaseService;
import com.base.util.BeanCopyUtils;
import org.apache.commons.collections.CollectionUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

/**
 * Created by suvan on 2017/11/1.
 */
public class CountTask extends RecursiveTask<List<Map<String, AlarmSummary>>> {
    private Integer MAX = 100000;
    private Integer total;
    private Integer skip;

    public CountTask(Integer total, Integer skip) {
        this.total = total;
        this.skip = skip;
    }

    @Override
    protected List<Map<String, AlarmSummary>> compute() {
        List<Map<String, AlarmSummary>> result = new ArrayList<>();
        Integer step = total - skip;
        if (step <= MAX) {
            Map<String, AlarmSummary> countMap = new HashMap<>();
            List<Alarmeventdetail> detailList = null;
            AlarmSummary temp = null;
            String key;
            try {
                detailList = BaseService.selectPageAlarm(skip, step);
                System.out.println("skip:" + skip + ", total:" + total + ",共" + detailList.size() + "条数据");
                if (CollectionUtils.isNotEmpty(detailList)) {
                    for (Alarmeventdetail one : detailList) {
                        key = one.getGroupByKey();
                        if (countMap.containsKey(key)) {// 检测重复告警
                            temp = countMap.get(key);
                            temp.setTotal(temp.getTotal() + 1);
                        } else {
                            temp = BeanCopyUtils.copy(one, AlarmSummary.class);
                            temp.setTotal(1);
                        }
                        countMap.put(key, temp);
                    }
                }
            } catch(SQLException e){
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            result.add(countMap);
        } else {
            Integer totalPage = total % MAX == 0 ? total / MAX : total / MAX + 1;
            ArrayList<CountTask> subTasks = new ArrayList<>();
            for (int i = 0; i < totalPage; i++) {
                Integer temp_skip = i * MAX;
                Integer temp_total = temp_skip;
                if (i < totalPage - 1) {
                    temp_total += MAX;
                } else {
                    temp_total += total % MAX == 0 ? MAX : total % MAX;
                }
                CountTask subTask = new CountTask(temp_total, temp_skip);
                subTasks.add(subTask);
                subTask.fork();
            }

            for (CountTask task : subTasks) {
                result.addAll(task.join());
            }
        }
        return result;
    }
}
