package com.atguigu.common.to;

/**
 * @author Created by Fangzj
 * @data 2021/6/15 22:05
 **/
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpuBoundTo {
    private Long spuId;
    private BigDecimal buyBounds;
    private BigDecimal growBounds;
}