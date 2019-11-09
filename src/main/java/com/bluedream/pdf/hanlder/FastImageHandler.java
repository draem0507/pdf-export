package com.bluedream.pdf.hanlder;

import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author: 刘文鑫(liuwenxin03)
 * @date: 2019-11-09 18:44
 * @desc: 快四图片处理器，在PDF转图片的时候，采用串行模式
 */
public class FastImageHandler extends AbstractImageHandler {


    private final ExecutorService executorService = Executors.newFixedThreadPool(10);


    public FastImageHandler() {

    }

    public FastImageHandler(int type) {
        super(type);
    }


    @Override
    public int toWrite(File file, PDFRenderer renderer, File outDir, final int pageName, int pageIndex) throws Exception {


        List<Future> results = new ArrayList<>();

        results.add(executorService.submit(() -> {
            new WriteThead(file, renderer, outDir, pageName, pageIndex);

        }));

        for (Future future : results) {
            future.get();

        }
        executorService.shutdown();


        return pageName;
    }


    class WriteThead implements Runnable {
        File file;
        PDFRenderer renderer;
        File outDir;
        int pageName;
        int pageIndex;

        public WriteThead(File file, PDFRenderer renderer, File outDir, int pageName, int pageIndex) {
            this.file = file;
            this.renderer = renderer;
            this.outDir = outDir;
            this.pageName = pageName;
            this.pageIndex = pageIndex;
        }

        @Override
        public void run() {

            try {
                renderAndWriter(file, renderer, outDir, pageName, pageIndex);


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


}
