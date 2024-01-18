package org.quest94.demo.sharding.sphere;

import mybatis.mate.ddl.DdlScript;
import org.junit.jupiter.api.*;
import org.quest94.demo.sharding.sphere.entity.TestData;
import org.quest94.demo.sharding.sphere.enums.TestEnum;
import org.quest94.demo.sharding.sphere.mapper.TestDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Disabled
@SpringBootTest
class TestDataMapperTest {

    @Autowired
    private TestDataMapper testDataMapper;
    @Autowired
    private DdlScript ddlScript;

    @BeforeEach
    void truncateTable() throws Exception {
        ddlScript.run(new StringReader("TRUNCATE TABLE test_data;"));
    }

    @Test
    @Order(1)
    void insertBatch() {
        int size = 20;
        List<TestData> testDataList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String str = i + "条";
            TestEnum testEnum = i % 2 == 0 ? TestEnum.TWO : TestEnum.ONE;
            testDataList.add(new TestData().setTestLong(((long) i)).setTestEnum(testEnum).setTestStr(str));
        }
        Assertions.assertEquals(size, testDataMapper.insertBatchSomeColumn(testDataList));
        testDataList.forEach(System.err::println);
    }

    @Test
    @Order(1)
    void query() {
        int size = 20;
        List<TestData> testDataList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String str = i + "条";
            TestEnum testEnum = i % 2 == 0 ? TestEnum.TWO : TestEnum.ONE;
            testDataList.add(new TestData().setTestLong((long) i).setTestEnum(testEnum).setTestStr(str));
        }
        Assertions.assertEquals(size, testDataMapper.insertBatchSomeColumn(testDataList));
        testDataList.forEach(System.err::println);
    }

    @Test
    @Order(2)
    void testInsertAutoId() {
        // 自增 id 增长为 1
        TestData testData = new TestData().setTestLong(1L);
        testData.setTestEnum(TestEnum.ONE).setTestStr("abc");
        Assertions.assertEquals(testDataMapper.insert(testData), 1);
        testData = testDataMapper.selectById(testData.getId());
        Assertions.assertNotNull(testData);
        Assertions.assertEquals(1, testData.getTestEnum().getCode());
        System.err.println(testData);
        // 自增 id 增长为 2
        TestData testData2 = new TestData().setTestLong(1L);
        testData2.setTestEnum(TestEnum.TWO).setTestStr("def");
        Assertions.assertEquals(testDataMapper.insert(testData2), 1);
        testData2 = testDataMapper.selectById(testData2.getId());
        Assertions.assertNotNull(testData2);
        Assertions.assertEquals(2, testData2.getTestEnum().getCode());
        System.err.println(testData2);
    }
}
