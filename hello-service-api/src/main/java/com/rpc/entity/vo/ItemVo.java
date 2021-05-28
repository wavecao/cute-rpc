package com.rpc.entity.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * @author: 曹威
 * @date: 2020-06-01  21:52
 * @description:
 */
@Data
public class ItemVo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 商品id，同时也是商品编号
     */
    private String id;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品卖点
     */
    private String sellPoint;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 库存数量
     */
    private Integer num;

    /**
     * 售卖数量限制
     */
    private Integer limitNum;

    /**
     * 商品图片
     */
    private String[] image;

    /**
     * 所属分类
     */
    private Long cid;

    /**
     * 商品状态 1正常 0下架
     */
    private Boolean status;

    /**
     * 创建时间
     */

    private Date createTime;

    /**
     * 更新时间
     */

    private Date updateTime;
}
