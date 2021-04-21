package com.hhd.shiro.excpetion.handler;

import com.hhd.shiro.enums.ServerResponseEnum;
import com.hhd.shiro.vo.R;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public R UnAuthorizedExceptionHandler(UnauthorizedException e) {
        return R.error(ServerResponseEnum.UNAUTHORIZED);
    }

    @ExceptionHandler(UnknownAccountException.class)
    public R UnAuthorizedExceptionHandler(UnknownAccountException e) {
        return R.error(ServerResponseEnum.ACCOUNT_NOT_EXIST);
    }

    @ExceptionHandler(DisabledAccountException.class)
    public R UnAuthorizedExceptionHandler(DisabledAccountException e) {
        return R.error(ServerResponseEnum.ACCOUNT_IS_DISABLED);
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    public R UnAuthorizedExceptionHandler(IncorrectCredentialsException e) {
        return R.error(ServerResponseEnum.INCORRECT_CREDENTIALS);
    }

    @ExceptionHandler(Exception.class)
    public R UnAuthorizedExceptionHandler(Exception e) {
        e.printStackTrace();
        return R.error(ServerResponseEnum.ERROR);
    }
}
