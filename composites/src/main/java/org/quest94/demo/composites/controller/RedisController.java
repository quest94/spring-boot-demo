package org.quest94.demo.composites.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * <P>ClassName: RedisController
 * <P>Description: redis测试控制器
 *
 * @author quec1994
 * @version V1.0, 2019/2/28
 **/
@RestController
@RequiredArgsConstructor
@Api(tags = "redis 测试API")
public class RedisController {
    @NonNull StringRedisTemplate srt;

    @PostMapping("redis/{key}/{value}")
    @ApiOperation(value = "设置缓存")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "键", dataType = "String", paramType = "path", example = "112233aabb", required = true),
            @ApiImplicitParam(name = "value", value = "值", dataType = "String", paramType = "path", example = "445566ccdd", required = true)
    })
    public String set(@PathVariable("key") @NotBlank(message = "键不能为空") String key, @PathVariable("value") @NotBlank(message = "值不能为空") String value) {
        //注意这里的 key不能为null spring 内部有检验
        srt.opsForValue().set(key, value);
        return key + "," + value;
    }

    @GetMapping("redis/{key}")
    @ApiOperation(value = "根据key获取缓存")
    @ApiImplicitParam(name = "key", value = "键", dataType = "String", paramType = "path", example = "112233aabb", required = true)
    public String get(@PathVariable("key") @NotBlank(message = "键不能为空") String key) {
        return "key=" + key + ",value=" + srt.opsForValue().get(key);
    }
}
