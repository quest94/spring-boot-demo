package org.quest94.demo.composites.service.impl;

import org.quest94.demo.composites.service.UnitTestService;
import org.springframework.stereotype.Service;

/**
 * <P>ClassName: UnitTestServiceImpl
 * <P>Description: 单元测试实现类
 *
 * @author qyz12
 * @version V1.0, 2019/3/7
 **/
@Service
public class UnitTestServiceImpl implements UnitTestService {

    /**
     * 为了测试，这里直接返回传入的值
     */
    @Override
    public String process(String msg) {
        return msg;
    }

}
