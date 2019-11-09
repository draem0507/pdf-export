package com.bluedream.pdf.hanlder;

import java.awt.image.BufferedImage;

/**
 * @author: 刘文鑫(liuwenxin03)
 * @date: 2019-11-09 16:54
 * @desc: 图片处理器
 */
public interface ImageHandler {


    /**
     * 合并图片  （注意：必须两张图片长宽一致）
     *
     * @param files
     * @param type  type 1 横向拼接， 2 纵向拼接
     * @return
     */
    BufferedImage mergeImages(String[] files, int type);


    /**
     * 裁剪图片
     *
     * @param source
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    BufferedImage cropImage(BufferedImage source,
                            int x, int y, int width, int height);


    /**
     * pdf 转图片
     * @param sourceFile pdf文件全路径
     * @param targetPath 转出的文件路径
     */
   void  convert(String sourceFile, String targetPath) throws Exception;



    }
