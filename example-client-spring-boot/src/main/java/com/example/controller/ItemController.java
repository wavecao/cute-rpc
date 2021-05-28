package com.example.controller;

import com.rpc.annotation.RpcReference;
import com.rpc.entity.Item;
import com.rpc.entity.R;
import com.rpc.entity.vo.ItemVo;
import com.rpc.sevice.ItemService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author 曹威
 * @since 2020-05-29
 */
@RestController
@RequestMapping("/item")
@CrossOrigin
public class ItemController {

    @RpcReference
    ItemService itemService;

    @GetMapping("/all")
    public R getAllItem() {
        return R.ok().data("items", itemService.getAllItem());
    }

    @GetMapping()
    public R getItemByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        return R.ok().data("items", itemService.getItemByPage(page, size)).data("total", itemService.list().size());
    }

    @GetMapping("/{itemId}")
    public R getItemById(@PathVariable String itemId) {
        return R.ok().data("items", itemService.getItemById(itemId));
    }

    @DeleteMapping("/{itemId}")
    public R delete(@PathVariable long itemId) {
        itemService.deleteById(itemId);
        return R.ok().data("items", "删除成功");
    }

    @PutMapping("/edit")
    public R edit(@RequestBody Item item) {
        if (item == null) {
            throw new IllegalArgumentException("内容为空");
        }
        List<ItemVo> itemVos = itemService.edit(item);
        return R.ok().data("items", itemVos);
    }

    @PutMapping("/edit")
    public R add(@RequestBody Item item) {
        if (item == null) {
            throw new IllegalArgumentException("内容为空");
        }
        List<ItemVo> itemVos = itemService.add(item);
        return R.ok().data("items", itemVos);
    }
}

