package org.quest94.demo.sharding.sphere.config.sharding.sphere;

import com.google.common.collect.Range;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;
import org.apache.shardingsphere.sharding.exception.syntax.UnsupportedShardingOperationException;

import java.util.*;

public class MyComplexKeysShardingAlgorithm implements ComplexKeysShardingAlgorithm<Long> {

    private static final String SHARDING_COLUMNS_KEY = "sharding-columns";

    private Properties props;

    /**
     * 保留配置的分片键。在当前算法其实是没有用的。
     */
    private Collection<String> shardingColumns;

    @Override
    public void init(Properties props) {
        this.props = props;
        this.shardingColumns = getShardingColumns(props);
    }

    private Collection<String> getShardingColumns(Properties props) {
        return Arrays.asList(props.getProperty(SHARDING_COLUMNS_KEY, "").split(","));
    }

    /**
     * 实现自定义分片算法
     *
     * @param availableTargetNames     在 actual-nodex 中配置了的所有数据分片
     * @param complexKeysShardingValue 组合分片键
     * @return 目标分片集合
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Long> complexKeysShardingValue) {
        // select * from test_data where id in (xxx,xx,x) and test_long between {{lowerEndpoint}} and {{upperEndpoint}};
        Collection<Long> idColl = complexKeysShardingValue.getColumnNameAndShardingValuesMap().get("id");
        Range<Long> testIntRange = complexKeysShardingValue.getColumnNameAndRangeValuesMap().get("test_long");
        // 拿到 test_long 的查询范围
        Long lowerEndpoint = testIntRange.lowerEndpoint();
        Long upperEndpoint = testIntRange.upperEndpoint();
        // 如果下限 > 上限
        if (lowerEndpoint > upperEndpoint) {
            // 抛出异常，终止去数据库查询的操作
            throw new UnsupportedShardingOperationException("empty record query", "test_data");
        } else if (upperEndpoint < 0 || lowerEndpoint > 100) {
            // 如果查询范围明显超出范围
            // 抛出异常，终止去数据库查询的操作
            throw new UnsupportedShardingOperationException("error range query param", "test_data");
        }
        // 查询范围没有问题后，就按照id分片
        List<String> result = new ArrayList<>();
        // 操作的逻辑表
        String logicTableName = complexKeysShardingValue.getLogicTableName();
        for (Long id : idColl) {
            String targetTable = logicTableName + "_" + (((id + 1) % 4) / 2 + 1);
            if (availableTargetNames.contains(targetTable)) {
                result.add(targetTable);
            }
        }
        return result;
    }

    @Override
    public Properties getProps() {
        return props;
    }

}
