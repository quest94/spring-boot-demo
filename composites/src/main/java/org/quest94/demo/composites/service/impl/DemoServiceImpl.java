package org.quest94.demo.composites.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.quest94.demo.composites.service.IDemoService;
import org.springframework.stereotype.Service;

/**
 * <P>ClassName: DemoServiceImpl
 * <P>Description: 例子业务的处理服务实现类
 *
 * @author quec1994
 * @version V1.0, 2019/1/22
 **/
@Service
@Slf4j
public class DemoServiceImpl implements IDemoService {

    @Override
    public String demo() {
        return "demo";
    }
}
