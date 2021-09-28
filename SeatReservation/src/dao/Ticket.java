package dao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class Ticket {

	//	Ticketsテーブルの要素
	int event_id;
	String identification_code; //ランダムに発行される文字列
	Date sold_at;
	Date punched_at;

	// イベントテーブルの要素
	// int event_id;
	String shop_name;
	String title;
	int capacity;
	String 	description;
	Date start_at;

	public Ticket(int event_id, String identification_code, Date sold_at, Date punched_at, String shop_name,
			String title, String description, Date start_at) {
		this.event_id = event_id;
		this.identification_code = identification_code;
		this.sold_at = sold_at;
		this.punched_at = punched_at;
		this.shop_name = shop_name;
		this.title = title;
		this.description = description;
		this.start_at = start_at;
	}
//チケット単体のコンストラクタ
//チケット認証で使う
	public Ticket(int event_id, String identification_code, Date sold_at, Date punched_at) {
		this(event_id, identification_code, sold_at, punched_at, null, null, null, null);
	}
//イベントテーブル要素のコンストラクタ
// Event.javaと内容が重複するのでいらないかも
	public Ticket(int event_id, String title, String description, Date start_at) {
		this(event_id, null, null, null, null, title, description, start_at);
	}
	public Ticket(String identification_code, String title, Date start_at) {
		this(0, identification_code, null, null, null, title, null, start_at);//0のイベントは存在しないことにしたい
	}
	public Ticket(int event_id, String identification_code, Date sold_at) {
		this(event_id, identification_code, sold_at, null, null, null, null, null);
	}


	public String createCode() {
		String identification_code = null;
		UUID uuid = UUID.randomUUID();
        identification_code = uuid.toString();

		return identification_code;

	}



	public String QRcode(String identification_code ,String path ) {
        String content = identification_code; //identification_code
        int width = 200;
        int height = 200;
        // moveメソッドで相対パスが可能

        String output = path;


        try {
            QRCodeWriter qrWriter = new QRCodeWriter();

            //QRCodeWriter#encode()には以下の情報を渡す
            // (1)エンコード対象の文字列、バーコードに埋め込みたい情報
            // (2)出力するバーコードの書式
            // (3)イメージの幅
            // (4)イメージの高さ
            BitMatrix bitMatrix = qrWriter.encode(content, BarcodeFormat.QR_CODE, width, height);

            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

            //エンコードで得られたイメージを画像ファイルに出力する
            ImageIO.write(image, "png", new File(output));

            return output;


        } catch (WriterException e) {
            System.err.println("[" + content + "] をエンコードするときに例外が発生.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("[" + output + "] を出力するときに例外が発生.");
            e.printStackTrace();
        }

        return output;


	}


//以下セッター, ゲッター

	public int getEvent_id() {
		return event_id;
	}



	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}



	public String getIdentification_code() {
		return identification_code;
	}



	public void setIdentification_code(String identification_code) {
		this.identification_code = identification_code;
	}



	public Date getSold_at() {
		return sold_at;
	}



	public void setSold_at(Date sold_at) {
		this.sold_at = sold_at;
	}



	public Date getPunched_at() {
		return punched_at;
	}



	public void setPunched_at(Date punched_at) {
		this.punched_at = punched_at;
	}



	public String getShop_name() {
		return shop_name;
	}



	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Date getStart_at() {
		return start_at;
	}



	public void setStart_at(Date start_at) {
		this.start_at = start_at;
	}



}
