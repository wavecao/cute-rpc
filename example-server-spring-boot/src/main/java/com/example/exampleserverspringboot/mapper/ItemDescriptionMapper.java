package com.example.exampleserverspringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rpc.entity.ItemDescription;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 商品描述表 Mapper 接口
 * </p>
 *
 * @author 曹威
 * @since 2020-05-29
 */
@Mapper
public interface ItemDescriptionMapper extends BaseMapper<ItemDescription> {

}
