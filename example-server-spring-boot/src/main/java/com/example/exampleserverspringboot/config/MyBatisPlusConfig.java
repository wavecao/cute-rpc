package com.example.exampleserverspringboot.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan("com.example.exampleserverspringboot.mapper")
public class MyBatisPlusConfig {

    /**
     * SQL 执行性能分析插件
     * 开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长
     */
    //@Bean
    //@Profile({"dev","test"})// 设置 dev test 环境开启
    //public PerformanceInterceptor performanceInterceptor() {
    //    PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
    //    performanceInterceptor.setMaxTime(1000);//ms，超过此处设置的ms则sql不执行
    //    performanceInterceptor.setFormat(true);
    //    return performanceInterceptor;
    //}

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    //@Bean
    //public ISqlInjector sqlInjector() {
    //    return new LogicSqlInjector();
    //}

}
