package org.quest94.demo.sharding.sphere.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.quest94.demo.sharding.sphere.config.mybatis.plus.MyBaseMapper;
import org.quest94.demo.sharding.sphere.entity.TestData;

import java.util.List;
import java.util.Optional;

public interface TestDataMapper extends MyBaseMapper<TestData> {

    @Select("select * from test_data")
    List<TestData> getAllNoTenant();

    @Select("select * from test_data ${ew.customSqlSegment}")
    List<TestData> getByWrapper(@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select * from test_data where id = #{ooxx}")
    Optional<TestData> getById(Long id);

    @Select("select * from test_data")
    Page<TestData> myPage(Page<TestData> page);

    @Select("select * from test_data where mod(id,2)=0")
    List<TestData> unsupportSQL(Long id);

}
