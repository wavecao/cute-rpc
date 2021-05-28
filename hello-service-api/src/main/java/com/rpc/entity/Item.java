package com.rpc.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author 曹威
 * @since 2020-05-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Item implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 商品id，同时也是商品编号
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
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
    private String image;

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
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
