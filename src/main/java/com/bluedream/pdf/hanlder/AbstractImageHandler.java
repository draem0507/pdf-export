package com.bluedream.pdf.hanlder;

import com.bluedream.pdf.exception.ImageArgsException;
import com.bluedream.pdf.hanlder.merge.MergeImageEngine;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.util.Objects;

/**
 * @author: 刘文鑫(liuwenxin03)
 * @date: 2019-11-09 16:59
 * @desc: 图片处理器基类
 */
public class AbstractImageHandler implements ImageHandler {

    public static final Log LOGGER = LogFactory.getLog(AbstractImageHandler.class);


    protected int type;

    protected EngineSeletor engineSeletor;

    private MergeImageEngine mergeImageEngine;

    /**
     * 初始化默认引擎
     */
    public AbstractImageHandler() {

        this(EngineSeletor.MERGER_IMAGE_HORIZONTAL_TYPE);
    }


    public AbstractImageHandler(int type) {
        this.type = type;
        initEngine();
    }


    @Override
    public BufferedImage mergeImages(String[] files, int type) {
        int len = files.length;
        if (len < 1) {
            throw new ImageArgsException("图片数量小于1");
        }
        File[] src = new File[len];
        BufferedImage[] images = new BufferedImage[len];
        int[][] ImageArrays = new int[len][];
        for (int i = 0; i < len; i++) {
            try {
                src[i] = new File(files[i]);
                images[i] = ImageIO.read(src[i]);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            int width = images[i].getWidth();
            int height = images[i].getHeight();
            ImageArrays[i] = new int[width * height];
            ImageArrays[i] = images[i].getRGB(0, 0, width, height, ImageArrays[i], 0, width);
        }
        return getBufferedImage(images, ImageArrays);

    }


    @Override
    public BufferedImage cropImage(BufferedImage source, int x, int y, int width, int height) {

        Objects.requireNonNull(source, "source must not be null");


        BufferedImage cropResult = null;
        // 读取源图像
        int srcWidth = source.getWidth();
        // 源图高度
        int srcHeight = source.getHeight();
        if (srcWidth > 0 && srcHeight > 0) {
            Image image = source.getScaledInstance(srcWidth, srcHeight,
                    Image.SCALE_DEFAULT);
            // 四个参数分别为图像起点坐标和宽高
            // 即: CropImageFilter(int x,int y,int width,int height)
            ImageFilter cropFilter = new CropImageFilter(x, y, width, height);
            Image img = Toolkit.getDefaultToolkit().createImage(
                    new FilteredImageSource(image.getSource(),
                            cropFilter));
            cropResult = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = cropResult.getGraphics();
            // 绘制切割后的图
            g.drawImage(img, 0, 0, width, height, null);
            g.dispose();


        }
        return cropResult;
    }

    @Override
    public void convert(String sourceFile, String targetPath) throws Exception {


        File file = new File(sourceFile);
        PDDocument document = PDDocument.load(file);
        PDFRenderer renderer = new PDFRenderer(document);

        int pageTotal = document.getNumberOfPages();

        LOGGER.info("PDF总页数："+ pageTotal);


        File outDir = new File(targetPath);
        if (!outDir.exists()) {
            outDir.mkdirs();
        }

        if (!outDir.isDirectory()) {
            LOGGER.error("请填写正确的输出路径");

            System.exit(0);
        }

        int pageName = 0;
        toConvert(file, renderer, pageTotal, outDir, pageName);

        document.close();


    }

    protected void toConvert(File file, PDFRenderer renderer, int pageTotal, File outDir, int pageName) throws Exception {
        for (int pageIndex = 0; pageIndex < pageTotal; pageIndex++) {
            LOGGER.info("正在转换第"+(pageIndex+1)+"页");

            pageName = renderAndWriter(file, renderer, outDir, pageName, pageIndex);


        }
    }

    protected int toWrite(File file, PDFRenderer renderer, File outDir, int pageName, int pageIndex) throws Exception {
        return  renderAndWriter(file,renderer,outDir,pageIndex,pageIndex);
    }

    protected int renderAndWriter(File file, PDFRenderer renderer, File outDir, int pageName, int pageIndex) throws Exception {
        BufferedImage image = renderer.renderImageWithDPI(pageIndex, 700, ImageType.RGB);
        String fileName = outDir + "/" + file.getName() + "-" + (pageName++) + ".jpg";
        writeImage(image, fileName);
        return pageName;
    }

    protected void writeImage(BufferedImage image, String fileName) throws Exception {
        ImageIO.write(image, "jpg", new File(fileName));
    }


    protected void initEngine() {
        engineSeletor =new EngineSeletor();

        mergeImageEngine = engineSeletor.seletor(type);

    }


    protected BufferedImage getBufferedImage(BufferedImage[] images, int[][] imageArrays) {
        return mergeImageEngine.getBufferedImage(images, imageArrays);
    }


}
