package com.bluedream.pdf;

import com.bluedream.pdf.hanlder.AbstractImageHandler;
import com.bluedream.pdf.hanlder.CommonImageHandler;
import com.bluedream.pdf.hanlder.FastImageHandler;

import java.io.IOException;

/**
 * @author: 刘文鑫(liuwenxin03)
 * @date: 2019-11-09 18:18
 * @desc:
 */
public class PdfExportImageNew {


    /**
     * PDF转图片
     *
     * @param sourceFile
     * @param targetPath
     * @throws IOException
     */
    public static void convert(String sourceFile, String targetPath) throws Exception {


        AbstractImageHandler abstractImageHandler = new CommonImageHandler();


        abstractImageHandler.convert(sourceFile, targetPath);

    }


    /**
     * PDF转图片
     *
     * @param sourceFile
     * @param targetPath
     * @throws IOException
     */
    public static void convertFast(String sourceFile, String targetPath) throws Exception {


        AbstractImageHandler abstractImageHandler = new FastImageHandler();


        abstractImageHandler.convert(sourceFile, targetPath);

    }


    public static void main(String[] args)throws  Exception {


        String root = "/Users/wenxinliu/Documents/技术琐话/";


        String sourceFile = root + "美团-牟宗彦-美团收单供应链架构演进.pdf";
        String targetPath = root + "Images/美团-牟宗彦-美团收单供应链架构演进";


       // convert(sourceFile,targetPath); //cost 3min
        convertFast(sourceFile,targetPath);








    }


}
