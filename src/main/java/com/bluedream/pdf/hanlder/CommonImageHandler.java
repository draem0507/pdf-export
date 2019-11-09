package com.bluedream.pdf.hanlder;

/**
 * @author: 刘文鑫(liuwenxin03)
 * @date: 2019-11-09 18:20
 * @desc: 普通图片处理器，在PDF转图片的时候，是串行模式
 */
public class CommonImageHandler extends AbstractImageHandler {


    public CommonImageHandler() {

    }

    public CommonImageHandler(int type) {
        super(type);
    }
}
