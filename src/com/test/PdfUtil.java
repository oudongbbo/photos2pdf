package com.test;


import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;


public class PdfUtil {


    private static String imagesPath;
    private static int index = 20190000;
    private static int number = 0;
    private static String name = "友好农场";
    private static String filesPath;
    private static String pdfFolder = "output";
    private static String outputfilesPath;

    /**
     * @param fileName   生成pdf文件
     * @param imagesPath 需要转换的图片路径的数组
     */
    public static void imagesToPdf(String fileName, String imagesPath) {


        File output = new File(outputfilesPath);

        try {
            if (!output.exists()) {
                output.mkdir();
            }

            fileName = outputfilesPath + File.separator + fileName + index + ".pdf";
            File file = new File(fileName);


            System.out.println("====================>输出文件路径： " + fileName);

            // 第一步：创建一个document对象。
            Document document = new Document();
            document.setMargins(0, 0, 0, 0);
            // 第二步：
            // 创建一个PdfWriter实例，
            PdfWriter.getInstance(document, new FileOutputStream(file));
            // 第三步：打开文档。
            document.open();
            // 第四步：在文档中增加图片。
            File files = new File(imagesPath);
            String[] images = files.list();
            int len = images.length;

            for (int i = 0; i < len; i++) {
                if (images[i].toLowerCase().endsWith(".bmp")
                        || images[i].toLowerCase().endsWith(".jpg")
                        || images[i].toLowerCase().endsWith(".jpeg")
                        || images[i].toLowerCase().endsWith(".gif")
                        || images[i].toLowerCase().endsWith(".png")) {
                    String temp = imagesPath + "\\" + images[i];
                    Image img = Image.getInstance(temp);
                    img.setAlignment(Image.ALIGN_CENTER);
                    // 根据图片大小设置页面，一定要先设置页面，再newPage（），否则无效
                    document.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
                    document.newPage();
                    document.add(img);
                }
            }
            // 第五步：关闭文档。
            document.close();
            System.out.println("第 " + number + " 份文件处理完毕！" + '\n');
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {


        filesPath = args[0];
        outputfilesPath = args[1];
        String pdfName = args[2];

        if(pdfName!=null&&!pdfName.trim().equals("")){
            name=pdfName;
        }

        //遍历获取当前文档目录

        System.out.println("输入文件目录是：==============>" + filesPath);
        System.out.println("输出文件目录是：==============>" + outputfilesPath);


        File file = new File(filesPath);
        String[] list = file.list();

        //清理历史文件
        System.out.println("===========>清理历史文件" + '\n');
        File output = new File(outputfilesPath);
        if (output.exists()) {
            delDir(output);
        }


        ArrayList workList = new ArrayList<String>();


        //过滤无用文件
        for (int j = 0; j < list.length; j++) {
            String filepath = list[j];

            System.out.println(filesPath + File.separator + filepath);
            File file1 = new File(filesPath + File.separator + filepath);
            if (file1.list().length == 0) {
                System.out.println("该目录下没有可编辑文件===========> " + filesPath + File.separator + filepath);

            } else {
                workList.add(filesPath + File.separator + filepath);
            }


        }

        System.out.println('\n');


        System.out.println("===========> 共找到可编辑文件：" + workList.size() + '\n');

        for (int i = 0; i < workList.size(); i++) {
            index = ++index;
            String filepath = (String) workList.get(i);
            System.out.println("正在处理第 " + ++number + " 份文件！"+filepath);
            imagesToPdf(name, filepath);
        }

    }


    public static void delDir(File file) {

        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    delDir(files[i]);
                }
            }
            file.delete();
        } else {
            System.out.println("要删除的文件不存在" + '\n');
        }
    }

}