package com.bluedream.pdf.exception;

/**
 * @author: 刘文鑫(liuwenxin03)
 * @date: 2019-11-09 17:57
 * @desc:
 */
public class ImageException extends RuntimeException {


    protected String message;


    public ImageException(){}

    public ImageException(String message) {
        this.message = message;

    }
}
