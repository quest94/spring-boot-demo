package org.quest94.demo.composites.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.quest94.demo.composites.bean.AddCommodityDTO;
import org.quest94.demo.composites.config.advice.exception.CommonException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <P>ClassName: CommodityController
 * <P>Description: 商品控制器
 *
 * @author quest94
 * @version V1.0, 2019/1/30
 **/
@RestController
@Slf4j
public class CommodityController {

    @PostMapping("commodity")
    public String addCommodity(@Valid AddCommodityDTO dto) {
        String result;
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            log.error("添加商品传入参数格式化错误", e);
            throw new CommonException("添加商品传入参数格式化错误");
        }
        return result;
    }


}
