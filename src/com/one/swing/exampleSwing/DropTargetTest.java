package com.one.swing.exampleSwing;

/**
 * Created by vtstar on 2018/1/11.
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.IOException;

/**
 * Java Swing编程：拖放功能
 *
 * 拖放功能其实就像我们使用windows 的时候按住CTRL然后拖动某个图标，会复制该对象。这给用户提供了很棒的用户体验，没办法程序员总是给自己找麻烦，让用户觉得方便，其实这个功能AWT也提供了Swing话只是利用了这个，因为这和界面没什么关系。
 DropTarget（拖放目的地）eg（将图片拖入后显示）：
 * */
public class DropTargetTest {
    final int DESKTOP_WIDTH = 480;
    final int DESKTOP_HEIGHT = 360;
    final int FRAME_DISTANCE = 30;
    JFrame jFrame = new JFrame("测试拖放目标--把图片文件拖入该窗口");
    //定义一个虚拟桌面
    private JDesktopPane desktopPane = new JDesktopPane();
    //保存下一个内部窗口的坐标点
    private int nextFrameX;
    private int nextFrameY;
    //定义内部窗口为虚拟桌面的1/2大小
    private int width = DESKTOP_WIDTH/2;
    private int heigh = DESKTOP_HEIGHT/2;

    public void init(){
    // Dimension类 尺寸 封装了一个构件的高度和宽度
        desktopPane.setPreferredSize(new Dimension(DESKTOP_WIDTH,DESKTOP_HEIGHT));
        //将当前窗口 创建成托放源
        new DropTarget(jFrame, DnDConstants.ACTION_COPY,new ImageDropTargetListener());
        jFrame.add(desktopPane);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }

                                            //  拖拽
    class ImageDropTargetListener extends DropTargetAdapter {
        @Override
        public void drop(DropTargetDropEvent event) {
            //接受复制操作
            event.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
            //获取拖放的内容
            Transferable transferable = event.getTransferable();
            DataFlavor[] flavors = transferable.getTransferDataFlavors();
            //遍历拖放内容里的所有数据格式
            for(int i=0;i<flavors.length;i++){
                DataFlavor dataFlavor = flavors[i];
                try{
                    //如果拖放内容的数据格式是文件列表
                    if(dataFlavor.equals(DataFlavor.javaFileListFlavor)){
                        //取出拖放操作里的文件列表
                        java.util.List fileList = (java.util.List) transferable.getTransferData(dataFlavor);
                        for(Object f:fileList){
                            //显示每个文件
                            showImage((File)f,event);
                        }

//                        //  获取文件路径
//                        for(int j=0;j<fileList.size();j++){
//                            File file = (File) fileList.get(j);
//                            final URL transferURL = file.toURL();
//                            System.out.println("transferURL="+transferURL);  //  file:/C:/Users/vtstar/Pictures/0063s1UQgw1eyq6j6pzfpj30hs0hsmz7.jpg
//                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                //强制拖放操作结束，停止阻塞拖放源
                event.dropComplete(true);
            }
        }
    }

    //显示每个文件的工具方法 (多张图片)
    private void showImage(File file,DropTargetDropEvent event) throws IOException {
        //1-12 获取文件路径
//        final URL transferURL = file.toURL();
//        System.out.println("transferURL="+transferURL);
        Image image = ImageIO.read(file);
        if(image==null){
            //强制拖放操作结束，停止阻塞拖放源
            event.dropComplete(true);
            JOptionPane.showInternalMessageDialog(desktopPane,"系统不支持这种类型的文件");
            //方法返回，不会继续操作
            return;
        }
        ImageIcon imageIcon = new ImageIcon(image);
        //创建内部窗口显示该图片
        JInternalFrame jInternalFrame = new JInternalFrame(file.getName(),true,true,true,true);
        JLabel imageLable = new JLabel(imageIcon);
        // JScrollPane 管理视口、可选的垂直和水平 滚动条 以及可选的行和列标题视口。
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.add(imageLable);
        jInternalFrame.add(jScrollPane);
        //jInternalFrame.add(new JScrollPane(imageLable)); // 或者
       desktopPane.add(jInternalFrame);
        //设置内部窗口的原始位置（内部窗口默认大小是0*0, 放在 0,0位置）
        jInternalFrame.reshape(nextFrameX,nextFrameY,width,heigh);
        //使该窗口可见，并尝试选中它
        jInternalFrame.show();
        //计算下一个内部窗口的位置
        nextFrameX += FRAME_DISTANCE;
        nextFrameY += FRAME_DISTANCE;
        if(nextFrameX + width > desktopPane.getWidth()){
            nextFrameX = 0;
        }
        if(nextFrameY + width > desktopPane.getHeight()){
            nextFrameY = 0;
        }
        // 将文件路径   乱码？？？
//        JEditorPane pane = new JEditorPane();
//        pane.setPage(transferURL);
//        jFrame.getContentPane().add(new JScrollPane(pane),BorderLayout.CENTER);
    }

    public static void main(String[] args){
        new DropTargetTest().init();
    }
}


