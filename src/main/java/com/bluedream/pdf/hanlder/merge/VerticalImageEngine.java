package com.bluedream.pdf.hanlder.merge;

import com.bluedream.pdf.exception.ImageArgsException;

import java.awt.image.BufferedImage;

/**
 * @author: 刘文鑫(liuwenxin03)
 * @date: 2019-11-09 17:50
 * @desc:
 */
public class VerticalImageEngine implements MergeImageEngine {
    @Override
    public BufferedImage getBufferedImage(BufferedImage[] images, int[][] imageArrays) {
        int newHeight = 0;
        int newWidth = 0;


        for (int i = 0; i < images.length; i++) {


            newWidth = newWidth > images[i].getWidth() ? newWidth : images[i].getWidth();
            newHeight += images[i].getHeight();

        }

        if (newHeight < MIN_MERGE_IMAGE_HEIGHT) {
            throw new ImageArgsException("when to vertical  image, one of image file's height must be bigger than 1");

        }

        // 生成新图片

        BufferedImage ImageNew = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        int height_i = 0;
        int width_i = 0;
        for (int i = 0; i < images.length; i++) {

            ImageNew.setRGB(0, height_i, newWidth, images[i].getHeight(), imageArrays[i], 0, newWidth);
            height_i += images[i].getHeight();

        }
        return ImageNew;
    }
}
