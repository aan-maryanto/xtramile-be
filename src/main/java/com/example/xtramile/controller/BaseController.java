package com.example.xtramile.controller;

import com.example.xtramile.common.constant.ConstantUtil;
import com.example.xtramile.dto.BaseResponse;
import org.springframework.http.HttpStatus;

public class BaseController {

    public <T> BaseResponse<T> toResponse(T data) {
        return BaseResponse.<T>builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(ConstantUtil.SUCCESS)
                .data(data)
                .build();
    }
}
