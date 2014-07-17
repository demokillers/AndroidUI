package communicate;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.json.simple.JSONObject;

import android.util.Log;

public class GetuiSdkHttpPost {

	public static String httpPost(String action, Map<String, Object> map, int CONNECTION_TIMEOUT, int READ_TIMEOUT) {

		String param = JSONObject.toJSONString(map);

		if (param != null) {

			URL url = null;

			try {
				url = new URL(PushConfig.SERVICEURL + action);
				HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
				urlConn.setDoInput(true); // 设置输入流采用字节流
				urlConn.setDoOutput(true); // 设置输出流采用字节流
				urlConn.setRequestMethod("POST");
				urlConn.setUseCaches(false); // 设置缓存
				urlConn.setRequestProperty("Charset", "utf-8");
				urlConn.setConnectTimeout(CONNECTION_TIMEOUT);
				urlConn.setReadTimeout(READ_TIMEOUT);

				urlConn.connect(); // 连接既往服务端发送消息
				Log.i("HttpPost", "connected to server");

				DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
				dop.write(param.getBytes("utf-8"));
				Log.i("HttpPost", "sending data");
				dop.flush(); // 发送，清空缓存
				dop.close(); // 关闭

				// 下面开始做接收工作
				BufferedReader bufferReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
				Log.i("HttpPost", "sending finished");
				String result = ""; // 获取服务器返回数据
				String readLine = null;
				while ((readLine = bufferReader.readLine()) != null) {
					result += readLine;
				}
				bufferReader.close();
				urlConn.disconnect();

				return result;

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 连接服务器失败
			return "error";
		}
		return "param is null";
	}
	
	/**
	 * 生成Sign方法
	 */
	/*public static String makeSign(String masterSecret, Map<String, Object> params) throws IllegalArgumentException {
		if (masterSecret == null || params == null) {
			throw new IllegalArgumentException("masterSecret and params can not be null.");
		}

		if (!(params instanceof SortedMap)) {
			params = new TreeMap<String, Object>(params);
		}

		StringBuilder input = new StringBuilder(masterSecret);
		for (Entry<String, Object> entry : params.entrySet()) {
			Object value = entry.getValue();
			if (value instanceof String || value instanceof Integer || value instanceof Long) {
				input.append(entry.getKey());
				input.append(entry.getValue());
			}
		}

		return getMD5Str(input.toString());
	}*/

	/**
	 * MD5加密
	 */
	/*public static String getMD5Str(String sourceStr) {
		byte[] source = sourceStr.getBytes();
		String s = null;
		char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		java.security.MessageDigest md = null;
		try {
			md = java.security.MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// #debug
			e.printStackTrace();
		}
		if (md == null)
			return null;
		md.update(source);
		byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
		// 用字节表示就是 16 个字节
		char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
		// 所以表示成 16 进制需要 32 个字符
		int k = 0; // 表示转换结果中对应的字符位置
		for (int i = 0; i < 16; i++) {
			// 从第一个字节开始，对 MD5 的每一个字节
			// 转换成 16 进制字符的转换
			byte byte0 = tmp[i]; // 取第 i 个字节
			str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
			// >>> 为逻辑右移，将符号位一起右移
			str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
		}
		s = new String(str); // 换后的结果转换为字符串
		return s;
	}*/

}
