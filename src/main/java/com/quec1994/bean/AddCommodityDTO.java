package com.quec1994.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <P>ClassName: AddCommodityDTO</P>
 * <P>Description: 商品的传入属性</P>
 *
 * @author quec1994
 * @version V1.0, 2019/1/30
 **/
@Data
@NoArgsConstructor
public class AddCommodityDTO {
    @NotBlank(message = "商品名称不能为空")
    String name;
    @NotNull(message = "价格不能为空")
    @DecimalMin(value = "0.01", message = "商品价格不能低于0.01元")
    Double price;
    @NotNull(message = "数量不能为空")
    @Min(value = 0L, message = "数量不能低于0个")
    Integer quantity;
}
