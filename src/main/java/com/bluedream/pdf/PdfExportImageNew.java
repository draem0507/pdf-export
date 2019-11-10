package com.bluedream.pdf;

import com.bluedream.pdf.exception.ImageArgsException;
import com.bluedream.pdf.hanlder.AbstractImageHandler;
import com.bluedream.pdf.hanlder.CommonImageHandler;
import com.bluedream.pdf.hanlder.FastImageHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;


/**
 * @author: 刘文鑫(liuwenxin03)
 * @date: 2019-11-09 18:18
 * @desc:
 */
public class PdfExportImageNew {
    public static final Log LOGGER = LogFactory.getLog(AbstractImageHandler.class);


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


    public static void main(String[] args) {



        /*String root = "/Users/wenxinliu/Documents/技术琐话/";
        String sourceFile = root + "美团-牟宗彦-美团收单供应链架构演进.pdf";
        String targetPath = root + "Images/美团-牟宗彦-美团收单供应链架构演进";*/

        if (null == args || args.length < 1) {

            throw new ImageArgsException("请输入PDF文件的完整路径");

        }

        String sourceFile = args[0];

        File file = new File(sourceFile);
        if (!file.exists()) {
            throw new ImageArgsException("Pdf文件找不到,当前路径为：" + sourceFile);

        }

        String targetPath = null;
        if (args.length == 2) {
            targetPath = args[1];
        } else {

            targetPath = file.getParent() + "/Images";
        }

        LOGGER.info("开始生成图片\n源文件 >> " + file.getName() + "\n输出后的路径 >>" + targetPath);


        // convert(sourceFile,targetPath); //cost 3min

        try {
            LOGGER.info("执行中，预计执行需要3~5分钟,请稍等...");
            convertFast(sourceFile, targetPath);
            LOGGER.info("执行完成,图片输出路径在：" + targetPath);
        } catch (Exception e) {
            LOGGER.info("执行异常:" + e.getMessage());

        }


    }


}
