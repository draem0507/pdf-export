package com.bluedream.pdf.hanlder.merge;

import com.bluedream.pdf.exception.ImageArgsException;

import java.awt.image.BufferedImage;

/**
 * @author: 刘文鑫(liuwenxin03)
 * @date: 2019-11-09 17:40
 * @desc:
 */
public class HorizontalImageEngine implements MergeImageEngine {

    @Override
    public BufferedImage getBufferedImage(BufferedImage[] images, int[][] imageArrays) {
        int newHeight = 0;
        int newWidth = 0;


        for (int i = 0; i < images.length; i++) {

            newHeight = newHeight > images[i].getHeight() ? newHeight : images[i].getHeight();
            newWidth += images[i].getWidth();

        }
        if (newWidth < MIN_MERGE_IMAGE_WIDTH) {
            throw new ImageArgsException("when to horizontal image ,one of  image file's width must be bigger than 1");
        }

        // 生成新图片
        BufferedImage ImageNew = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        int height_i = 0;
        int width_i = 0;
        for (int i = 0; i < images.length; i++) {
            ImageNew.setRGB(width_i, 0, images[i].getWidth(), newHeight, imageArrays[i], 0,
                    images[i].getWidth());
            width_i += images[i].getWidth();

        }
        return ImageNew;
    }
}
