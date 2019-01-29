package com.quec1994.business.demo.service.impl;

import com.quec1994.business.demo.service.IDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <P>ClassName: DemoServiceImpl</P>
 * <P>Description: 例子业务的处理服务实现类</P>
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
