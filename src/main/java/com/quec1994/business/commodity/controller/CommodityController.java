package com.quec1994.business.commodity.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quec1994.business.commodity.bean.AddCommodityDTO;
import com.quec1994.common.controllerAdvice.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <P>ClassName: CommodityController</P>
 * <P>Description: 商品控制器</P>
 *
 * @author quec1994
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
            throw new BusinessException("添加商品传入参数格式化错误");
        }
        return result;
    }


}
