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
				urlConn.setDoInput(true); // ���������������ֽ���
				urlConn.setDoOutput(true); // ��������������ֽ���
				urlConn.setRequestMethod("POST");
				urlConn.setUseCaches(false); // ���û���
				urlConn.setRequestProperty("Charset", "utf-8");
				urlConn.setConnectTimeout(CONNECTION_TIMEOUT);
				urlConn.setReadTimeout(READ_TIMEOUT);

				urlConn.connect(); // ���Ӽ�������˷�����Ϣ
				Log.i("HttpPost", "connected to server");

				DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
				dop.write(param.getBytes("utf-8"));
				Log.i("HttpPost", "sending data");
				dop.flush(); // ���ͣ���ջ���
				dop.close(); // �ر�

				// ���濪ʼ�����չ���
				BufferedReader bufferReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
				Log.i("HttpPost", "sending finished");
				String result = ""; // ��ȡ��������������
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
			// ���ӷ�����ʧ��
			return "error";
		}
		return "param is null";
	}
	
	/**
	 * ����Sign����
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
	 * MD5����
	 */
	/*public static String getMD5Str(String sourceStr) {
		byte[] source = sourceStr.getBytes();
		String s = null;
		char hexDigits[] = { // �������ֽ�ת���� 16 ���Ʊ�ʾ���ַ�
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
		byte tmp[] = md.digest(); // MD5 �ļ�������һ�� 128 λ�ĳ�������
		// ���ֽڱ�ʾ���� 16 ���ֽ�
		char str[] = new char[16 * 2]; // ÿ���ֽ��� 16 ���Ʊ�ʾ�Ļ���ʹ�������ַ���
		// ���Ա�ʾ�� 16 ������Ҫ 32 ���ַ�
		int k = 0; // ��ʾת������ж�Ӧ���ַ�λ��
		for (int i = 0; i < 16; i++) {
			// �ӵ�һ���ֽڿ�ʼ���� MD5 ��ÿһ���ֽ�
			// ת���� 16 �����ַ���ת��
			byte byte0 = tmp[i]; // ȡ�� i ���ֽ�
			str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // ȡ�ֽ��и� 4 λ������ת��,
			// >>> Ϊ�߼����ƣ�������λһ������
			str[k++] = hexDigits[byte0 & 0xf]; // ȡ�ֽ��е� 4 λ������ת��
		}
		s = new String(str); // ����Ľ��ת��Ϊ�ַ���
		return s;
	}*/

}
