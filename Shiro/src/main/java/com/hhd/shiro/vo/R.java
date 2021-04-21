package com.hhd.shiro.vo;

import com.hhd.shiro.enums.ServerResponseEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class R<T> {
    // 响应码
    private Integer code;

    // 描述信息
    private String message;

    // 响应内容
    private T data;

    private R(ServerResponseEnum responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }

    private R(ServerResponseEnum responseCode, T data) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.data = data;
    }

    private R(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 返回成功信息
     * @param data      信息内容
     * @param <T>
     * @return
     */
    public static<T> R success(T data) {
        return new R<>(ServerResponseEnum.SUCCESS, data);
    }

    /**
     * 返回成功信息
     * @return
     */
    public static R success() {
        return new R(ServerResponseEnum.SUCCESS);
    }

    /**
     * 返回错误信息
     * @param responseCode      响应码
     * @return
     */
    public static R error(ServerResponseEnum responseCode) {
        return new R(responseCode);
    }
}
