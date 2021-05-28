package com.example.exampleserverspringboot.handler;

import com.rpc.entity.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(com.example.exampleserverspringboot.handler.DoShopException.class)
    @ResponseBody
    public R error(DoShopException e) {
        e.printStackTrace();
        return R.error().message(e.getMsg()).code(e.getCode());
    }

}
