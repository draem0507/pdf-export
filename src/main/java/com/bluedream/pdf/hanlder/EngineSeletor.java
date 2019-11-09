package com.bluedream.pdf.hanlder;

import com.bluedream.pdf.exception.NoSuchEngineException;
import com.bluedream.pdf.hanlder.merge.VerticalImageEngine;
import com.bluedream.pdf.hanlder.merge.HorizontalImageEngine;
import com.bluedream.pdf.hanlder.merge.MergeImageEngine;

/**
 * @author: 刘文鑫(liuwenxin03)
 * @date: 2019-11-09 17:52
 * @desc:
 */
public class EngineSeletor {


    /**
     * 合并图片模式（横向）
     */
    public static final int MERGER_IMAGE_HORIZONTAL_TYPE = 1;


    /**
     * 合并图片模式（纵向）
     */
    public static final int MERGER_IMAGE_VERTICAL_TYPE = 1;


    public MergeImageEngine seletor(int type) {

        if (type == MERGER_IMAGE_HORIZONTAL_TYPE) {

            return new HorizontalImageEngine();
        } else if (type == MERGER_IMAGE_VERTICAL_TYPE) {

            return new VerticalImageEngine();
        }


        throw new NoSuchEngineException("fail to get   MergeImageEngine by type :" + type);

    }


}
