package guodu.net.send;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import guodu.net.util.ConfigContainer;

public class SendPotMessqge extends Send{

	public String sendMessages(String name, String password, String mobile, String bumen, String username ,String information) {
		String str = null;
 		String URL = ConfigContainer.getMessageUrl();
 		str = "username=" + name + "&passwd=" + password + "&mobile=" + mobile +"&bumen=" + bumen +"&user=" + username +"&information=" + information;

		/* 使用post方式发送消息 */
 		System.out.println(URL);
		String response = postURL(str, URL);
		/* 返回响应 */
		return response;
	}
}
