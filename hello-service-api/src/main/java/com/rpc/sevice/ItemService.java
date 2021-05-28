package com.rpc.sevice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rpc.entity.Item;
import com.rpc.entity.dto.ItemDto;
import com.rpc.entity.vo.ItemVo;
import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author 曹威
 * @since 2020-05-29
 */
public interface ItemService extends IService<Item> {
    List<ItemVo> getAllItem();

    List<ItemVo> getItemByPage(int page, int size);

    ItemDto getItemById(String itemId);

    List<ItemVo> transToItemVo(List<Item> itemList);

    void deleteById(long id);

    List<ItemVo> edit(Item item);

    List<ItemVo> add(Item item);
}
