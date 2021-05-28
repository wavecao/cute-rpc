package com.rpc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品描述表
 * </p>
 *
 * @author 曹威
 * @since 2020-05-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ItemDescription implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 商品ID
     */
    @TableId(value = "item_id", type = IdType.ID_WORKER)
    private String itemId;

    /**
     * 商品描述
     */
    private String itemDesc;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
