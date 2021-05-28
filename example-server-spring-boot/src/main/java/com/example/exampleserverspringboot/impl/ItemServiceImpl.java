package com.example.exampleserverspringboot.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.exampleserverspringboot.handler.DoShopException;
import com.example.exampleserverspringboot.mapper.ItemMapper;
import com.rpc.annotation.RpcService;
import com.rpc.entity.Item;
import com.rpc.entity.ItemDescription;
import com.rpc.entity.dto.ItemDto;
import com.rpc.entity.vo.ItemVo;
import com.rpc.sevice.ItemDescriptionService;
import com.rpc.sevice.ItemService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * 商品表 服务实现类
 * @author 曹威
 * @since 2020-05-29
 */
@RpcService
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

    @Autowired
    ItemDescriptionService itemDescriptionService;

    @Override
    public List<ItemVo> getAllItem() {
        List<Item> itemList = this.list();
        if (itemList.size() == 0) {
            throw new DoShopException(400, "未查到商品数据");
        }
        return transToItemVo(itemList);
    }

    @Override
    public List<ItemVo> getItemByPage(int page, int size) {
        Page<Item> itemPage = new Page<>(page, size);
        IPage<Item> itemIPage = baseMapper.selectPage(itemPage, null);
        List<Item> records = itemIPage.getRecords();
        if (records.size() == 0) {
            throw new DoShopException(400, "未查到商品数据");
        }
        return transToItemVo(records);
    }

    /**
     * 根据id获取商品的详细信息
     * @param itemId 商品id
     * @return 商品信息
     */
    @Override
    public ItemDto getItemById(String itemId) {
        ItemDto itemDto = new ItemDto();
        Item item = baseMapper.selectById(itemId);
        if (item == null) {
            throw new DoShopException(400, "无该商品");
        }
        ItemDescription itemDescription = itemDescriptionService.getById(itemId);
        if (itemDescription.getItemDesc() == null) {
            itemDescription.setItemDesc("无具体描述");
        }
        BeanUtils.copyProperties(item, itemDto);
        BeanUtils.copyProperties(itemDescription, itemDto);
        itemDto.setImage(item.getImage().split(","));
        itemDto.setItemDesc(itemDescription.getItemDesc().split(","));
        itemDto.setStock(item.getNum());
        return itemDto;
    }

    @Override
    public List<ItemVo> transToItemVo(List<Item> itemList) {
        List<ItemVo> itemVoList = new ArrayList<>();
        for (Item item : itemList) {
            ItemVo itemVo = new ItemVo();
            BeanUtils.copyProperties(item, itemVo);
            itemVo.setImage(item.getImage().split(","));
            itemVoList.add(itemVo);
        }
        return itemVoList;
    }

    @Override
    public void deleteById(long id) {
        if (id <= 0) {
            throw new DoShopException(400,"商品ID错误");
        }
        baseMapper.deleteById(id);
    }

    @Override
    public List<ItemVo> edit(Item item) {
        if (item == null || Long.parseLong(item.getId()) <= 0) {
            throw new DoShopException(400, "商品信息不完整");
        }
        baseMapper.updateById(item);
        return transToItemVo(Collections.singletonList(baseMapper.selectById(item)));
    }

    @Override
    public List<ItemVo> add(Item item) {
        if (item == null) {
            throw new DoShopException(400, "商品信息不可为空");
        }
        boolean save = save(item);
        if (!save) {
            throw new DoShopException(400, "添加失败");
        }
        return transToItemVo(Collections.singletonList(baseMapper.selectById(item)));
    }
}
