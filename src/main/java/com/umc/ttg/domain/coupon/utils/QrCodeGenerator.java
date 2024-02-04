package com.umc.ttg.domain.coupon.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.umc.ttg.domain.store.entity.Store;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;


public class QrCodeGenerator {

    public static MultipartFile generateQrCode(Store store) throws WriterException, IOException {
        String qrCodePath = "/Robin/QrImage/";
        String qrCodeName = qrCodePath + store.getName() + store.getId() + "-QRCODE.jpeg";
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(
                "ID:  " + store.getId() + "\n " +
                        "CouponName: " + store.getName() + "\n ", BarcodeFormat.QR_CODE, 400, 400);

        Path path = FileSystems.getDefault().getPath(qrCodeName);
        MatrixToImageWriter.writeToPath(bitMatrix, "JPEG", path);
        return BitMatrixToMultipartFileConverter.convertToMultipartFile(bitMatrix);

     }

}
