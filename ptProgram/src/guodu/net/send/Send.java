package guodu.net.send;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import guodu.net.interf.SendInf;

public abstract class Send implements SendInf{
	
	@Override
	public String sendMessages(String user, String password) {
		return null;
	}

	@Override
	public String sendMessages(String name, String password, String mobile, String bumen, String username ,String information) {
		return null;
	}
	

	/** post方式 发送url串 */
	/**
	 * @param commString 需要发送的url参数串
	 * @param address 需要发送的url地址
	 * @return rec_string 国都返回的自定义格式的串
	 * @catch Exception
	 */
	public String postURL(String commString, String address) {
		String rec_string = "";
		URL url = null;
		HttpURLConnection urlConn = null;
		try {
			/* 得到url地址的URL类 */
			url = new URL(address);
			/* 获得打开需要发送的url连接 */
			urlConn = (HttpURLConnection) url.openConnection();
			/* 设置连接超时时间 */
			urlConn.setConnectTimeout(30000);
			/* 设置读取响应超时时间 */
			urlConn.setReadTimeout(30000);
			/* 设置post发送方式 */
			urlConn.setRequestMethod("GET");
			/* 发送commString */
			urlConn.setDoOutput(true);
			OutputStream out = urlConn.getOutputStream();
			out.write(commString.getBytes("UTF-8"));
			out.flush();
			out.close();
			/* 发送完毕 获取返回流，解析流数据 */
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					urlConn.getInputStream(), "UTF-8"));
			StringBuffer sb = new StringBuffer();
			int ch;
			while ((ch = rd.read()) > -1) {
				sb.append((char) ch);
			}
			rec_string = sb.toString().trim();
			/* 解析完毕关闭输入流 */
			rd.close();
		} catch (Exception e) {
			/* 异常处理 */
			rec_string = "-107";
		} finally {
			/* 关闭URL连接 */
			if (urlConn != null) {
				urlConn.disconnect();
			}
		}
		/* 返回响应内容 */
		return rec_string;
	}
}
