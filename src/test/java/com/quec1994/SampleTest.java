package com.quec1994;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <P>ClassName: SampleTest</P>
 * <P>Description: 简单测试类</P>
 *
 * @author quec1994
 * @version V1.0, 2019/1/31
 **/
public class SampleTest {

    @Test
    public void testLoscalDateTime() {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")));
    }

}

