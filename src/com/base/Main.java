package com.base;

import com.base.model.AlarmSummary;
import com.base.service.BaseService;
import com.base.task.CountTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class Main {
    public static final Integer max = 1000000;

    public static void main(String[] args) throws Exception{
        long begin = System.currentTimeMillis();
        System.out.println("开始处理数据。。。");

        Integer totalCount = BaseService.selectAlarmCount();
        System.out.println("需要处理：" + totalCount + "条数据");

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(totalCount, 0);
        ForkJoinTask<List<Map<String, AlarmSummary>>> result = forkJoinPool.submit(task);
        try {
            List<Map<String, AlarmSummary>> resultList = result.get();
            Map<String, AlarmSummary> all = new HashMap<>();
            AlarmSummary temp = null;
            for (Map<String, AlarmSummary> one : resultList) {
                for (String key : one.keySet()) {
                    if (all.containsKey(key)) {// 检测重复告警
                        all.get(key).setTotal(all.get(key).getTotal() + one.get(key).getTotal());
                    } else {
                        all.put(key, one.get(key));
                    }
                }
            }

            Integer total = 0;
            for (AlarmSummary model : all.values()) {
                total += model.getTotal();
                BaseService.insertAlarmSummary(model);
            }
            System.out.println("共处理:" + total + "条数据");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("结束处理数据。。。用时：" + (end - begin) / 1000 + "秒");
    }
}
