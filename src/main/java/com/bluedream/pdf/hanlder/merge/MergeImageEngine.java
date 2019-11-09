package com.bluedream.pdf.hanlder.merge;

import java.awt.image.BufferedImage;

/**
 * @author: 刘文鑫(liuwenxin03)
 * @date: 2019-11-09 17:42
 * @desc:
 */
public interface MergeImageEngine {


    /**
     * 合并图片最小宽度
     */
    int MIN_MERGE_IMAGE_WIDTH = 1;

    /**
     * 合并图片最小高度
     */
    int MIN_MERGE_IMAGE_HEIGHT = 1;

    /**
     * 处理图片
     * @param images
     * @param imageArrays
     * @return
     */
    BufferedImage getBufferedImage(BufferedImage[] images, int[][] imageArrays);


}
