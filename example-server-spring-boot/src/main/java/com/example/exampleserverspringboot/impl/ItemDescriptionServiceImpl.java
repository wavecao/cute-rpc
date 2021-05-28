package com.example.exampleserverspringboot.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.exampleserverspringboot.mapper.ItemDescriptionMapper;
import com.rpc.annotation.RpcService;
import com.rpc.entity.ItemDescription;
import com.rpc.sevice.ItemDescriptionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品描述表 服务实现类
 * </p>
 *
 * @author 曹威
 * @since 2020-05-29
 */
@RpcService
@Service
public class ItemDescriptionServiceImpl extends
    ServiceImpl<ItemDescriptionMapper, ItemDescription> implements
    ItemDescriptionService {

    @Override
    public ItemDescription getById(String itemId) {
        return baseMapper.selectById(itemId);
    }
}
