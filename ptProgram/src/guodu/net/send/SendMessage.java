package guodu.net.send;

import guodu.net.interf.SendInf;
import guodu.net.util.ConfigContainer;
import guodu.net.util.Util;

public class SendMessage extends Send{
	
	/**
     * 登录认证
     * @param username 
     * 				用户名
     * @param password 
     * 				密码          
     * @return response 
     * 				0:成功，－1:失败
     * */
	public String sendMessages(String user, String password){
		
 		String str = null;
 		ConfigContainer cf = ConfigContainer.getInstance();
 		String URL = cf.getUrl();
 		str = "username=" + user + "&passwd=" + password;
		/* 消息参数 */

//				content = URLEncoder.encode(content,"GBK");

 		Util ut = new Util();
		/* 使用post方式发送消息 */
		String response = postURL(str, URL);
		/* 返回响应 */
		return response;
	}
	
	public static void main(String[] args){
		String result = new SendMessage().sendMessages("test" , "test1");
		System.out.println("===== " + result);
	}

}
