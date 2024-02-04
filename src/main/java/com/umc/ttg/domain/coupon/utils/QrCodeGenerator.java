package com.umc.ttg.domain.coupon.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.umc.ttg.domain.coupon.entity.Coupon;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;


public class QrCodeGenerator {
    public static void generateQrCode(Coupon coupon) throws WriterException, IOException {
        String qrCodePath = "/Robin/QrImage/";
        String qrCodeName = qrCodePath + coupon.getName() + coupon.getId() + "-QRCODE.png";
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(
                "ID:  " + coupon.getId() + "\n " +
                        "CouponName: " + coupon.getName() + "\n ", BarcodeFormat.QR_CODE, 400, 400);

        Path path = FileSystems.getDefault().getPath(qrCodeName);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
     }

}
