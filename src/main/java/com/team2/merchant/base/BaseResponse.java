package com.team2.merchant.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BaseResponse<T> {
    private boolean status;
    private String errorMessage;
    private T data;

    public BaseResponse(T data) {
        this.data = data;
        this.status = true;
    }

    public BaseResponse(String errorMessage) {
        this.errorMessage = errorMessage;
        this.status = false;
    }
}
