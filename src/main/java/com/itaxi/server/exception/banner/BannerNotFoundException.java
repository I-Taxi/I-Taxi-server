package com.itaxi.server.exception.banner;

import org.springframework.http.HttpStatus;

import static com.itaxi.server.exception.Message.BANNER_NOT_FOUND;

public class BannerNotFoundException extends BannerException{
    public BannerNotFoundException(HttpStatus httpStatus) {
        super(BANNER_NOT_FOUND, httpStatus);
    }
}
