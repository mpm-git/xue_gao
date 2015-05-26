package per.cz.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

public class EncryptionUtil {

	public static String getMd5(String str) {
		try {
			//确定计算方法
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			//加密后的字符串
			return base64en.encode(md5.digest(str.getBytes()));
		} catch(Exception e) {
			return "";
		}
	}
	//将source进行MD5加密，获取加密后的字符串
		public static String getMD5(byte[] source) {
			String s = null;
			// 用来将字节转换成 16 进制表示的字符
			char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(source);
				byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
				// 用字节表示就是 16 个字节
				char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
				// 所以表示成 16 进制需要 32 个字符
				int k = 0; // 表示转换结果中对应的字符位置
				for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
					// 转换成 16 进制字符的转换
					byte byte0 = tmp[i]; // 取第 i 个字节
					str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
					// >>> 为逻辑右移，将符号位一起右移
					str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
				}
				s = new String(str); // 换后的结果转换为字符串

			} catch (Exception e) {
				e.printStackTrace();
			}
			return s;
		}
	/**
	 * AES加密
	 * @param content 	  待加密数值
	 * @param encrypt 加密或者解密（true加密，false解密）
	 */
	public static String setAES(String content, boolean encrypt){
		int mode;
		byte[] contentByte;
		//若是加密String转byte[]，若是解密十六进制String转byte[]
		if (encrypt) {
			mode = Cipher.ENCRYPT_MODE;
			contentByte = content.getBytes();
		}else{
			mode = Cipher.DECRYPT_MODE;
			contentByte = parseHexStr2Byte(content);
		}
		//		writeLog(Util.class, content);
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");  
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			random.setSeed("z0x9c8V&".getBytes("utf-8"));
			kgen.init(128, random);
			SecretKey secretKey = kgen.generateKey();  
			byte[] enCodeFormat = secretKey.getEncoded();  
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");  
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器   
			cipher.init(mode, key);// 初始化   
			byte[] result = cipher.doFinal(contentByte);  
			//加密结果二进制转换为十六进制
			if (encrypt) {
				return new String(parseByte2HexStr(result).getBytes());
			}
			return new String(result); // 加密 

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
		return null;
	}
	/**将二进制转换成16进制 
	 * @param buf 
	 * @return 
	 */  
	public static String parseByte2HexStr(byte buf[]) {  
		StringBuffer sb = new StringBuffer();  
		for (int i = 0; i < buf.length; i++) {  
			String hex = Integer.toHexString(buf[i] & 0xFF);  
			if (hex.length() == 1) {  
				hex = '0' + hex;  
			}  
			sb.append(hex.toUpperCase());  
		}  
		return sb.toString();  
	} 
	/**将16进制转换为二进制 
	 * @param hexStr 
	 * @return 
	 */  
	public static byte[] parseHexStr2Byte(String hexStr) {  
		if (hexStr.length() < 1)  
			return null;  
		byte[] result = new byte[hexStr.length()/2];  
		for (int i = 0;i< hexStr.length()/2; i++) {  
			int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
			int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
			result[i] = (byte) (high * 16 + low);  
		}  
		return result;  
	}  
}
