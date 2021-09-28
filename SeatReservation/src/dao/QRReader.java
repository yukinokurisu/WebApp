package dao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;


public class QRReader {

	String filePath;
	String identification_code;


	public QRReader() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public String Read(String filePath) {
		this.filePath = filePath;

	     //読み取り処理
		 BufferedImage image = null;
		try {
			image = ImageIO.read(new File(filePath));
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	        LuminanceSource source = new BufferedImageLuminanceSource(image);
	        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
	        Reader reader = new MultiFormatReader();
	        Result decodeResult = null;

			try {
				decodeResult = reader.decode(bitmap);
			} catch (NotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (ChecksumException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (FormatException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

        //デコード処理
        String result = decodeResult.getText();
        //標準出力
        //System.out.format("読み取り結果=%1$s", result);

		return result;
	}

}
