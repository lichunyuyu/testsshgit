package com.one.javatest.exportExcel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

/**
 * Created by vtstar on 2018/4/2.
 */
public class Transparence {

    public  static void main(String[] args)  {
//        BufferedImage imageSrc = new BufferedImage();
//        Object mask = new Object();
//
//        createImageByMaskColor(imageSrc,mask);

    }
    /*
     * 构建imageSrc的拷贝，象素颜色为mask的显示为透明
     *
     * @param imageSrc 原始图像
     * @param mask mask为Object的实例，因为如果imageSrc为灰度图，或者为索引颜色图，则mask应为指定
     *                 灰度或索引的数值。如果imageSrc是其他模式的图像，则使用一个Color对象指定颜色值
     * @return 返回imageSrc的拷贝，象素颜色为mask的显示为透明
     */
    public static BufferedImage createImageByMaskColor(BufferedImage imageSrc, Object mask) {
        int x = imageSrc.getWidth(null);
        int y = imageSrc.getHeight(null);
        Raster rasterSrc = imageSrc.getRaster();
        BufferedImage imageDes = new BufferedImage(x, y, BufferedImage.TYPE_4BYTE_ABGR);
        WritableRaster rasterDes = imageDes.getRaster();

        int[] src = null;
        int[] des = new int[4];
        int type = imageSrc.getType();
        if (type == BufferedImage.TYPE_BYTE_GRAY) {
            int cmask = (Integer)mask;
            while (--x >= 0)
                for (int j = 0; j < y; ++j) {
                    src = rasterSrc.getPixel(x, j, (int[])null);
                    if (src[0] == cmask)
                        des[3] = 0;
                    else {
                        des[0] = src[0];
                        des[1] = src[0];
                        des[2] = src[0];
                        des[3] = 255;
                    }
                    rasterDes.setPixel(x, j, des);
                }
        } else if (type == BufferedImage.TYPE_BYTE_INDEXED) {
            int cmask = (Integer)mask;
            ColorModel cm = imageSrc.getColorModel();
            Object data = null;
            while (--x >= 0)
                for (int j = 0; j < y; ++j) {
                    src = rasterSrc.getPixel(x, j, (int[])null);
                    if (src[0] == cmask) {//透明
                        des[3] = 0;//
                    } else {
                        data = rasterSrc.getDataElements(x, j, null);
                        int argb = cm.getRGB(data);
                        Color color = new Color(argb, true);
                        des[0] = color.getRed();
                        des[1] = color.getGreen();
                        des[2] = color.getBlue();
                        des[3] = 255;
                    }
                    rasterDes.setPixel(x, j, des);

                }
        } else {
            ColorModel cm = imageSrc.getColorModel();
            Color cmask = (Color)mask;
            Object data = null;
            int maskR, maskG, maskB;
            maskR = cmask.getRed();
            maskG = cmask.getGreen();
            maskB = cmask.getBlue();
            while (--x >= 0)
                for (int j = 0; j < y; ++j) {
                    data = rasterSrc.getDataElements(x, j, null);
                    int rgb = cm.getRGB(data);
                    int sr, sg, sb;
                    sr = (rgb & 0xFF0000)>>16;
                    sg = (rgb & 0xFF00)>>8;
                    sb = rgb & 0xFF;
                    if (sr != maskR || sg != maskG || sb != maskB) {
                        des[0] = sr;
                        des[1] = sg;
                        des[2] = sb;
                        des[3] = 255;
                    }
                    else
                        des[3] = 0;
                    rasterDes.setPixel(x, j, des);
                }
        }
        return imageDes;
    }

    /*
     * 构建imageSrc的拷贝，象素颜色为mask的显示为透明
     *
     * @param imageSrc 原始图像
     * @param mask 无论原始图像的色彩模式为何种模式，mask统一传入一个
     *                 Color类型的对象指定希望显示为透明的色彩值
     * @return 返回imageSrc的拷贝，象素颜色为mask的显示为透明
     */
    public static BufferedImage createImageByMaskColorEx(BufferedImage imageSrc, Color mask) {
        int x, y;
        x = imageSrc.getWidth(null);
        y = imageSrc.getHeight(null);
        Raster rasterSrc = imageSrc.getRaster();
        BufferedImage imageDes = new BufferedImage(x, y, BufferedImage.TYPE_4BYTE_ABGR);
        WritableRaster rasterDes = imageDes.getRaster();

        int[] src = null;
        int[] des = new int[4];

        ColorModel cm = imageSrc.getColorModel();
        Color cmask = (Color)mask;
        Object data = null;
        int maskR, maskG, maskB;
        maskR = cmask.getRed();
        maskG = cmask.getGreen();
        maskB = cmask.getBlue();
        while (--x >= 0)
            for (int j = 0; j < y; ++j) {
                data = rasterSrc.getDataElements(x, j, null);
                int rgb = cm.getRGB(data);
                int sr, sg, sb;
                sr = (rgb & 0xFF0000)>>16;
                sg = (rgb & 0xFF00)>>8;
                sb = rgb & 0xFF;
                if (sr == maskR && sg == maskG && sb == maskB)
                    des[3] = 0;
                else {
                    des[0] = sr;
                    des[1] = sg;
                    des[2] = sb;
                    des[3] = 255;
                }
                rasterDes.setPixel(x, j, des);
            }
        return imageDes;
    }
    /*
     * 创建渐进透明的图像，
     * @param imageSrc imageSrc必须是一个单字节灰度图，
     *                    在生成的图像中原始图像的黑色区域将被视为透明，
     *                    透明度由黑色到白色逐渐降低，到白色时完全不透明
     * <b>createGrayImage()方法返回的BufferedImage对象不能作为这个方法输入
     *            因为createGrayImage()方法返回的是一个四字节灰度图，而该方法的
     *            参数需要的是一个单字节的灰度图（虽然也很容易实现四字节灰度图
     *            的输入，但，或许还是不好）<b>
     * @return 渐进的灰度透明图
     */
    public static BufferedImage createGradientTransparenceImage(BufferedImage imageSrc)
            throws Exception {
        int type = imageSrc.getType();
        if (type != BufferedImage.TYPE_BYTE_GRAY && type != BufferedImage.TYPE_BYTE_INDEXED)
            throw new Exception("Method: " +
                    "Transparence.createGradientTrasparenceImage(BufferedImage imageSrc)" +
                    " only supports image of which type is gray. please checking your image file's type!");
        int x, y;
        x = imageSrc.getWidth();
        y = imageSrc.getHeight();
        Raster rasterSrc = imageSrc.getRaster();
        BufferedImage imageDes = new BufferedImage(x, y, BufferedImage.TYPE_4BYTE_ABGR);
        WritableRaster rasterDes = imageDes.getRaster();
        int[] src = new int[1];
        int[] des = new int[]{255, 255, 255, 255};
        while (--x >= 0)
            for (int j = 0; j < y; ++j) {
                rasterSrc.getPixel(x, j, src);
                des[3] = src[0];
                rasterDes.setPixel(x, j, des);
            }
        return imageDes;
    }
    /*
     * 创造原始图像的灰度图,如果原始图像有透明通道，则会被保留
     * @param imageSrc 原始图像
     * @param reserve 是否保留原始的灰度信息，如果不保留，所有的有效颜色都
     *                    设置为level值（level:0 - 255）
     * @param level 如果不保留原始的灰度信息，则将图像设置成统一的一个灰度值
     *                配合其他方法，可以很容易实现Office2003图标浮起时下面的阴影。
     * @return 原始图像的灰度图
     */
    public static BufferedImage createGrayImage(BufferedImage imageSrc, boolean reserve, int level) {
        int x, y;
        x = imageSrc.getWidth();
        y = imageSrc.getHeight();

        Raster rasterSrc = imageSrc.getRaster();
        ColorModel cm = imageSrc.getColorModel();
        BufferedImage imageDes = new BufferedImage(x, y, BufferedImage.TYPE_4BYTE_ABGR);
        WritableRaster rasterDes = imageDes.getRaster();

        int[] des = new int[]{level, level, level, 0};
        Object inData = null;
        int rgb = 0;
        int sr = 0;
        int sg = 0;
        int sb = 0;
        while (--x >= 0)
            for (int j = 0; j < y; ++j) {
                inData = rasterSrc.getDataElements(x, j, null);
                if (reserve) {
                    rgb = cm.getRGB(inData);
                    sr = (rgb & 0xFF0000)>>16;
                    sg = (rgb & 0xFF00)>>8;
                    sb = rgb & 0xFF;
                    des[0] = des[1] = des[2] = (sr * 77 + sg * 150 + sb * 29 + 128)>>8;
                }
                //设置透明通道
                des[3] = cm.getAlpha(inData);

                rasterDes.setPixel(x, j, des);
            }
        return imageDes;
    }
    /*
     * 创建图像，imageMask中被mask指定的区域，在imageSrc中将显示为透明。
     *
     * imageSrc与imageMask必须长，宽一致
     * @param imageSrc 原始图像
     * @param imageMask 掩码图像
     * @param mask 透明通道，当imageMask是一个索引图、灰度图，mask必须是
     *                一个0-255的数值对象。
     *                 当imageMask是一个其他色彩模式的图像，则mask必须是一个Color对象。
     * @return 原始图像的拷贝。imageMask中被mask指定的区域，在imageSrc中将显示为透明。
     */
    public static BufferedImage createImageByMaskImage(BufferedImage imageSrc,
                                                       BufferedImage imageMask,
                                                       Object mask) {
        int x = imageSrc.getWidth();
        int y = imageSrc.getHeight();
        Raster rasterSrc = imageSrc.getRaster();
        ColorModel cm = imageSrc.getColorModel();
        Raster rasterMask = imageMask.getRaster();

        BufferedImage imageDes = new BufferedImage(x, y, BufferedImage.TYPE_4BYTE_ABGR);
        WritableRaster rasterDes = imageDes.getRaster();

        Object inData = null;
        int[] maskArr = null;
        int[] des = new int[]{0, 0, 0, 0};
        int rgb = 0;
        int sr = 0;
        int sg = 0;
        int sb = 0;
        int type =     imageMask.getType();
        if (type == BufferedImage.TYPE_BYTE_GRAY || type == BufferedImage.TYPE_BYTE_INDEXED || type == BufferedImage.TYPE_BYTE_BINARY) {
            int cmask = (Integer)mask;
            maskArr = new int[1];
            while (--x >= 0)
                for (int j = 0; j < y; ++j) {
                    rasterMask.getPixel(x, j, maskArr);
                    if (maskArr[0] != cmask) {
                        inData = rasterSrc.getDataElements(x, j, null);
                        rgb = cm.getRGB(inData);
                        sr = (rgb & 0xFF0000)>>16;
                        sg = (rgb & 0xFF00)>>8;
                        sb = rgb & 0xFF;
                        des[0] = sr;
                        des[1] = sg;
                        des[2] = sb;
                        des[3] = 255;
                    }
                    else
                        des[3] = 0;
                    rasterDes.setPixel(x, j, des);
                }
        } else {
            Color cmask = (Color)mask;
            int cmr = cmask.getRed();
            int cmg = cmask.getGreen();
            int cmb = cmask.getBlue();

            int mr, mg, mb;
            Object maskInData = null;
            ColorModel maskCm = imageMask.getColorModel();
            int mrgb = 0;
            while (--x >= 0)
                for (int j = 0; j < y; ++j) {
                    maskInData = rasterMask.getDataElements(x, j, null);
                    mrgb = maskCm.getRed(maskInData);
                    mr = (mrgb & 0xFF0000)>>16;
                    mg = (mrgb & 0xFF00)>>8;
                    mb = mrgb & 0xFF;
                    if (cmr != mr || cmg != mg || cmb != mb) {
                        inData = rasterSrc.getDataElements(x, j, null);
                        rgb = cm.getRGB(inData);
                        sr = (rgb & 0xFF0000)>>16;
                        sg = (rgb & 0xFF00)>>8;
                        sb = rgb & 0xFF;
                        des[0] = sr;
                        des[1] = sg;
                        des[2] = sb;
                        des[3] = 255;
                    }
                    else
                        des[3] = 0;
                    rasterDes.setPixel(x, j, des);
                }
        }
        return imageDes;
    }
    /*
     * 创建图像，imageMask中被mask指定的区域，在imageSrc中将显示为透明。
     * imageSrc与imageMask必须长，宽一致
     * @param imageSrc 原始图像
     * @param imageMask 掩码图像
     * @param mask 透明通道，必须是一个Color对象
     * @return 原始图像的拷贝。imageMask中被mask指定的区域，在imageSrc中将显示为透明。
     */
    public static BufferedImage createImageByMaskImageEx(BufferedImage imageSrc,
                                                         BufferedImage imageMask,
                                                         Color mask) {
        int x = imageMask.getWidth();
        int y = imageMask.getHeight();
        Raster rasterSrc = imageSrc.getRaster();
        ColorModel cm = imageSrc.getColorModel();
        Raster rasterMask = imageMask.getRaster();

        BufferedImage imageDes = new BufferedImage(x, y, BufferedImage.TYPE_4BYTE_ABGR);
        WritableRaster rasterDes = imageDes.getRaster();

        Object inData = null;
        int[] maskArr = null;
        int[] des = new int[]{0, 0, 0, 0};
        int rgb = 0;
        int sr = 0;
        int sg = 0;
        int sb = 0;

        Color cmask = (Color)mask;
        int cmr = cmask.getRed();
        int cmg = cmask.getGreen();
        int cmb = cmask.getBlue();

        int mr, mg, mb;
        Object maskInData = null;
        ColorModel maskCm = imageMask.getColorModel();
        int mrgb = 0;
        while (--x >= 0)
            for (int j = 0; j < y; ++j) {
                maskInData = rasterMask.getDataElements(x, j, null);
                mrgb = maskCm.getRGB(maskInData);
                mr = (mrgb & 0xFF0000)>>16;
                mg = (mrgb & 0xFF00)>>8;
                mb = mrgb & 0xFF;
                if (cmr != mr || cmg != mg || cmb != mb) {
                    inData = rasterSrc.getDataElements(x, j, null);
                    rgb = cm.getRGB(inData);
                    sr = (rgb & 0xFF0000)>>16;
                    sg = (rgb & 0xFF00)>>8;
                    sb = rgb & 0xFF;
                    des[0] = sr;
                    des[1] = sg;
                    des[2] = sb;
                    des[3] = 255;
                }
                else
                    des[3] = 0;
                rasterDes.setPixel(x, j, des);
            }
        return imageDes;
    }
    /*
     * 创建一副imageSrc根据imageGray的灰度变化而产生透明渐进变化的图像
     * imageGray的大小可以是imageSrc的一部分
     * @param imageSrc 原始图像
     * @param sx 原始图与灰度图开始交错的坐标x点
     * @param sy 原始图与灰度图开始交错的坐标y点
     * @param imageGray 灰度图
     * @return 返回imageSrc的拷贝，根据imageGray产生透明的渐进变化
     */
    public static BufferedImage createGradientTransparenceByGrayImage(BufferedImage imageSrc, int sx, int sy, BufferedImage imageGray)
            throws Exception {
        int type = imageGray.getType();
        if (type != BufferedImage.TYPE_BYTE_GRAY)
            throw new Exception("Method: " +
                    "Transparence.createGradientTransparenceByGrayImage(BufferedImage imageSrc, BufferedImage imageGray)" +
                    " only supports that imageGray's type is gray and color bit is 8. please checking your image file's type!");
        int x, y;
        x = imageGray.getWidth();
        y = imageGray.getHeight();
        Raster rasterGray = imageGray.getRaster();
        Raster rasterSrc = imageSrc.getRaster();
        ColorModel cmSrc = imageSrc.getColorModel();

        BufferedImage imageDes = new BufferedImage(x, y, BufferedImage.TYPE_4BYTE_ABGR);
        WritableRaster rasterDes = imageDes.getRaster();

        int[] gray = new int[1];
        int[] des = new int[]{0, 0, 0, 0};
        Object inData = null;
        int rgb = 0;
        while (--x >= 0)
            for (int j = 0; j < y; ++j) {
                rasterGray.getPixel(x, j, gray);
                if (gray[0] != 0) {
                    inData = rasterSrc.getDataElements(x+sx, j+sy, null);
                    rgb = cmSrc.getRGB(inData);
                    des[0] = (rgb & 0xFF0000)>>16;
                    des[1] = (rgb & 0xFF00)>>8;
                    des[2] = rgb & 0xFF;
                    des[3] = gray[0];
                }
                else
                    des[3] = 0;
                rasterDes.setPixel(x, j, des);
            }
        return imageDes;
    }

}
