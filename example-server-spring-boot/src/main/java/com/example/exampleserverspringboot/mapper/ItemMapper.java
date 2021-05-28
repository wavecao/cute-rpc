package com.example.exampleserverspringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rpc.entity.Item;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author 曹威
 * @since 2020-05-29
 */
@Mapper
public interface ItemMapper extends BaseMapper<Item> {

}
