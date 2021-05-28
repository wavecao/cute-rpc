package com.example.exampleserverspringboot.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoShopException extends RuntimeException {

    private Integer code;

    private String msg;

    @Override
    public String toString() {
        return "Exception{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
