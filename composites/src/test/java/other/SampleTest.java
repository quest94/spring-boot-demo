package other;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <P>ClassName: SampleTest
 * <P>Description: 简单测试类
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

