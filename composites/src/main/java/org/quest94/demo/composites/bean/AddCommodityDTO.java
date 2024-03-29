package org.quest94.demo.composites.bean;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <P>ClassName: AddCommodityDTO
 * <P>Description: 商品的传入属性
 *
 * @author quest94
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
