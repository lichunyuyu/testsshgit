package com.one.javatest.exportPictureRGB;

/**
 * Created by vtstar on 2018/10/23.
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/** http://www.sdky.org/news/2018-04-07/141109.html
 *     java图片色阶调整和亮度调整代码示例
 对Java图片处理的内容涉猎不深，言辞简陋望请见谅。
 java实现色阶调整，即调整图片rgb分量，进而也可以调节图片亮度。
 * */
public class ImageChangeL {

    //测试代码
    public static void main(String[] args) {
        //文件与BufferedImage间的转换
        BufferedImage bi=file2img("test.jpg");
        //读取图片
        BufferedImage bii=img_color_gradation(bi,100,0,0);
        img2file(bii,"jpg","test1.jpg");
        //生成图片
    }
    //色阶调整代码
    //图片色阶调整，调整rgb的分量
    public static BufferedImage img_color_gradation(BufferedImage imgsrc, int r, int g, int b) {
        try {
            //创建一个不带透明度的图片
            BufferedImage back=new BufferedImage(imgsrc.getWidth(), imgsrc.getHeight(),BufferedImage.TYPE_INT_RGB);
            int width = imgsrc.getWidth();
            int height = imgsrc.getHeight();
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int pixel = imgsrc.getRGB(j, i);
                    Color color = new Color(pixel);
                    int red= color.getRed()+r; if(red>255) red=255; if(red<0) red=0;
                    int green= color.getGreen()+g; if(green>255) green=255; if(green<0) green=0;
                    int blue= color.getBlue()+b; if(blue>255) blue=255; if(blue<0) blue=0;
                    color = new Color(red,green,blue);
                    int x=color.getRGB();
                    back.setRGB(j,i,x);
                }
            }
            return back;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //图片读取，和存储函数
    //读取图片
    public static BufferedImage file2img(String imgpath) {
        try {
            BufferedImage bufferedImage= ImageIO.read(new File(imgpath));
            return bufferedImage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //保存图片,extent为格式，"jpg"、"png"等
    public static void img2file(BufferedImage img, String extent, String newfile) {
        try {
            ImageIO.write(img, extent, new File(newfile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
/**
    分享：
            下面先给出亮度和对比度的计算公式
            （RGB表示原图的色彩分量的值，nRGB表不处理后的值,mBrightness表示调整的亮度值，mContrast表示调整的对比度值,avg表示整个图像像素的平均值）
            亮度:nRGB=RGB+mBrightness
            对比度：nRGB=(RGB-avg)*(1-percent%)+avg percent%取值范围为(-1~1) 0为原始值
            对比度公式也很好证明，将其展开
            nRGB=RGB-RGB*percent%-avg+avg*percent%+avg
            nRGB=RGB-RGB*percent%+avg*percent%
            对于整个图像矩阵来说，要保证亮度不变，即整个矩阵的代数和不变。
            而avg=(RGB1+RGB2+....RGBn)/n (1)
            (nRGB1+nRGB2+.....nRGBn)=(RGB1+RGB2+...RGBn)+n*avg*percent%-(RGB1+RGB2+...RGBn)*percent% (2)
            将（1）式代入（2)式即可
*/