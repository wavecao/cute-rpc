package com.rpc.sevice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rpc.entity.ItemDescription;

/**
 * <p>
 * 商品描述表 服务类
 * </p>
 *
 * @author 曹威
 * @since 2020-05-29
 */
public interface ItemDescriptionService extends IService<ItemDescription> {

    ItemDescription getById(String itemId);

}
