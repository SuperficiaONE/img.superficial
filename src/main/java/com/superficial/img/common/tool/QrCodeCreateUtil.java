package com.superficial.img.common.tool;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * 二维码生成和读的工具类
 */
public class QrCodeCreateUtil {
    private static Integer onColor = 0xFF000000;
    private static Integer offColor = 0xFFFFFFFF;

    /**
     * 生成包含字符串信息的二维码图片
     *
     * @param outputStream 文件输出流路径
     * @param content      二维码携带信息
     * @param qrCodeSize   二维码图片大小
     * @param imageFormat  二维码的格式
     * @throws WriterException
     * @throws IOException
     */
    public static boolean createQrCode(OutputStream outputStream, String content, int qrCodeSize, String imageFormat) {
        //定义二维码的参数
        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);
        //生成二维码
        try {
            //OutputStream stream = new OutputStreamWriter();
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hints);

            // MatrixToImageWriter.writeToPath(bitMatrix, format, file);

            MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, outputStream);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean createQrCode(OutputStream outputStream, String content, int qrCodeSize, Image img, String imageFormat) {
        //定义二维码的参数
        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.MARGIN, 2);

        //生成二维码
        try {
            //OutputStream stream = new OutputStreamWriter();
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hints);
            // 设置 中间icon
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 读二维码并输出携带的信息
     */
    public static void readQrCode(InputStream inputStream) throws IOException {
        //从输入流中获取字符串信息
        BufferedImage image = ImageIO.read(inputStream);
        //将图像转换为二进制位图源
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        QRCodeReader reader = new QRCodeReader();
        Result result = null;
        try {
            result = reader.decode(bitmap);
        } catch (ReaderException e) {
            e.printStackTrace();
        }
        System.out.println(result.getText());
    }

    public static  void createBannerCode(OutputStream outputStream,String content ,Integer size ,String srcImagePath) throws IOException, WriterException {
        BufferedImage bufferedImage = QrCodeCreateUtil.genBarcode(content, size, size, srcImagePath);
        ImageIO.write(bufferedImage,"JPEG",outputStream);
    }

    public static BufferedImage genBarcode(String content, int width,
                                           int height, String srcImagePath) throws WriterException,
            IOException {
        // 读取源图像
        Integer IMAGE_WIDTH = 50;
        Integer IMAGE_HEIGHT = 50;
        BufferedImage scaleImage = scale(srcImagePath, IMAGE_WIDTH,
                IMAGE_HEIGHT, false);
        int[][] srcPixels = new int[IMAGE_WIDTH][IMAGE_HEIGHT];
        for (int i = 0; i < scaleImage.getWidth(); i++) {
            for (int j = 0; j < scaleImage.getHeight(); j++) {
                srcPixels[i][j] = scaleImage.getRGB(i, j);
            }
        }

        Map<EncodeHintType, Object> hint = new HashMap();
        //内容编码
        hint.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //错误等级
        hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //设置二维码外边框的空白区域的宽度
        hint.put(EncodeHintType.MARGIN, 1);
        // 生成二维码
        BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,
                width, height, hint);

        // 二维矩阵转为一维像素数组
        int halfW = matrix.getWidth() / 2;
        int halfH = matrix.getHeight() / 2;
        int FRAME_WIDTH = 2;
        int topLeftY = halfH - IMAGE_HEIGHT / 2 - FRAME_WIDTH;
        int topLeftX = halfW - IMAGE_WIDTH / 2 - FRAME_WIDTH;
        int bottomRightX = halfW + IMAGE_WIDTH / 2 + FRAME_WIDTH;
        int bottomRightY = halfH + IMAGE_HEIGHT / 2 + FRAME_WIDTH;


        int centerTopLeftY = halfH - IMAGE_HEIGHT / 2;
        int centerTopLeftX = halfW - IMAGE_WIDTH / 2;
        int centerBottomRightX = halfW + IMAGE_WIDTH / 2;
        int centerBottomRightY = halfH + IMAGE_HEIGHT / 2;
        int[] pixels = new int[width * height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                //判断是否是在中间图片区域
                if (inRegion(x, y, topLeftX, topLeftY, bottomRightX, bottomRightY)) {
                    if (inRegion(x, y, centerTopLeftX, centerTopLeftY, centerBottomRightX, centerBottomRightY)) {
                        pixels[y * width + x] = srcPixels[x - centerTopLeftX][y - centerTopLeftY];
                    } else {
                        pixels[y * width + x] = 0xfffffff;
                    }

                } else {
                    pixels[y * width + x] = matrix.get(x, y) ? 0xff000000
                            : 0xfffffff;
                }
            }
        }
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        image.getRaster().setDataElements(0, 0, width, height, pixels);

        return image;
    }

    public static Boolean inRegion(int x, int y, int topLeftX, int topLeftY, int bottomRightX, int bottomRightY) {
        if (x >= topLeftX && x < bottomRightX && y >= topLeftY && y < bottomRightY) {
            return true;
        }
        return false;
    }

    /**
     * 把传入的原始图像按高度和宽度进行缩放，生成符合要求的图标
     *
     * @param srcImageFile 源文件地址
     * @param height       目标高度
     * @param width        目标宽度
     * @param hasFiller    比例不对时是否需要补白：true为补白; false为不补白;
     * @throws IOException
     */
    private static BufferedImage scale(String srcImageFile, int height,
                                       int width, boolean hasFiller) throws IOException {
        double ratio = 0.0;
        // 缩放比例

        Image src=Toolkit.getDefaultToolkit().getImage(srcImageFile);
        BufferedImage srcImage=toBufferedImage(src);
       // File file = new File(srcImageFile);
       // BufferedImage srcImage = ImageIO.read(file);
        Image destImage = srcImage.getScaledInstance(width, height,
                BufferedImage.SCALE_SMOOTH);
        // 计算比例
        if ((srcImage.getHeight() > height) || (srcImage.getWidth() > width)) {
            if (srcImage.getHeight() > srcImage.getWidth()) {
                ratio = (new Integer(height)).doubleValue()
                        / srcImage.getHeight();
            } else {
                ratio = (new Integer(width)).doubleValue()
                        / srcImage.getWidth();
            }
            AffineTransformOp op = new AffineTransformOp(
                    AffineTransform.getScaleInstance(ratio, ratio), null);
            destImage = op.filter(srcImage, null);
        }
        if (hasFiller) {
            // 补白
            BufferedImage image = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D graphic = image.createGraphics();
            graphic.setColor(Color.PINK);
            graphic.fillRect(10, 10, width, height);
            graphic.drawRect(100, 360, width, height);
            if (width == destImage.getWidth(null)) {
                graphic.drawImage(destImage, 0,
                        (height - destImage.getHeight(null)) / 2,
                        destImage.getWidth(null), destImage.getHeight(null),
                        Color.white, null);
                Shape shape = new RoundRectangle2D.Float(0, (height - destImage.getHeight(null)) / 2, width, width, 20, 20);
                graphic.setStroke(new BasicStroke(5f));
                graphic.draw(shape);
            } else {
                graphic.drawImage(destImage,
                        (width - destImage.getWidth(null)) / 2, 0,
                        destImage.getWidth(null), destImage.getHeight(null),
                        Color.white, null);
                Shape shape = new RoundRectangle2D.Float((width - destImage.getWidth(null)) / 2, 0, width, width, 20, 20);
                graphic.setStroke(new BasicStroke(5f));
                graphic.draw(shape);
            }
            graphic.dispose();
            destImage = image;

        }
        return  toBufferedImage(destImage);
    }


    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        try {
            int transparency = Transparency.OPAQUE;
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(image.getWidth(null),
                    image.getHeight(null), transparency);
        } catch (HeadlessException e) {

        }
        if (bimage == null) {
            int type = BufferedImage.TYPE_INT_RGB;
            bimage = new BufferedImage(image.getWidth(null),
                    image.getHeight(null), type);
        }
        // Copy image to buffered image
        Graphics g = bimage.createGraphics();
        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return bimage;
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int width, int height, int type) {
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, width, null);
        g.dispose();
        return resizedImage;
    }

    public static BufferedImage convertToBufferedImage(Image image) {
        BufferedImage newImage = new BufferedImage(64, 64, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }
}