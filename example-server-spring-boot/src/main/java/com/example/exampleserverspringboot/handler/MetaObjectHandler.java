package com.example.exampleserverspringboot.handler;

import java.util.Date;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class MetaObjectHandler implements com.baomidou.mybatisplus.core.handlers.MetaObjectHandler {

    private static final String defaultAvatar = "http://do-shop.oss-cn-beijing.aliyuncs.com/2020/05/16/64c33229-cf32-42f8-a639-0a70a371b959.JPG";

    @Override
    public void insertFill(MetaObject metaObject) {

        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("isDeleted", false, metaObject);
//        if ( StringUtils.isEmpty((String)metaObject.getValue("username")) )
//            this.setFieldValByName("username", RandomName.getRandomName(), metaObject);
        if ( StringUtils.isEmpty((String)metaObject.getValue("avatar")) )
            this.setFieldValByName("avatar", defaultAvatar, metaObject);
        //this.setFieldValByName("paymentTime", new Date(), metaObject);
        this.setFieldValByName("buyerComment", false, metaObject);
        this.setFieldValByName("shippingInfo", "待发货", metaObject);
        this.setFieldValByName("shippingName", "自营空运，当日必达", metaObject);
        //this.setFieldValByName("isDefault", false, metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }


}
