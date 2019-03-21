package com.superficial.img.common.config;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;


import java.util.Date;

@Component
public class MybatisObjectHandler extends MetaObjectHandler  {

    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("createAt", new Date(),metaObject);
        setFieldValByName("updateAt",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateAt",new Date(),metaObject);
    }
}