package org.quest94.demo.sharding.sphere;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.quest94.demo.sharding.sphere.entity.TestData;
import org.quest94.demo.sharding.sphere.mapper.TestDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Disabled
@SpringBootTest
class ShardingSphereTest {

    @Autowired
    private TestDataMapper testDataMapper;

    @Test
    void queryTestData() {
        QueryWrapper<TestData> wrapper = new QueryWrapper<>();
//        wrapper.eq("id", 954865292503154690L);
        wrapper.in("id", 954865292503154690L, 954865292503154694L, 954865292503154698L, 5555L);
        // 带上排序条件不影响分片逻辑
//        wrapper.orderByDesc("test_long");
        List<TestData> testDataList = testDataMapper.getByWrapper(wrapper);
        testDataList.forEach(System.out::println);
    }

    @Test
    void queryTestDataRange() {
        QueryWrapper<TestData> wrapper = new QueryWrapper<>();
        wrapper.between("id", 954865292503154690L, 954865292503154694L);
        List<TestData> testDataList = testDataMapper.getByWrapper(wrapper);
        testDataList.forEach(System.out::println);
    }

    @Test
    void queryTestDataComplex() {
        QueryWrapper<TestData> wrapper = new QueryWrapper<>();
        wrapper.in("id", 954865292503154690L, 954865292503154694L);
        wrapper.between("test_long", 5L, 9L);
        List<TestData> testDataList = testDataMapper.getByWrapper(wrapper);
        testDataList.forEach(System.out::println);
    }

    @Test
    void unsupportTest() {
        List<TestData> testDataList = testDataMapper.unsupportSQL(954865292503154690L);
        testDataList.forEach(System.out::println);
    }

    @Test
    void queryTestDataByHint() {
        // 强制只查 test_data_1 表
        // hintManager 关闭的主要作用是消除ThreadLocal，释放内存。
        // HintManager 实现了 AutoCloseable 接口，所以建议使用 try-resource 的方式
        try (HintManager hintManager = HintManager.getInstance()) {
            // 注意这两个属性，databaseShardingValue 用于强制分库
//        hintManager.setDatabaseShardingValue(1L);
            // 强制只查 test_data_1 表
            hintManager.addTableShardingValue("test_data", "1");
            // select * from test_data;
            List<TestData> testDataList = testDataMapper.getByWrapper(null);
            testDataList.forEach(System.out::println);
            // 线程安全，所有用完要注意
//            hintManager.close();
        }

    }

}
