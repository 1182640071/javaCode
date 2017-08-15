package guodu.net.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Util {
	
	
	/**
	 * 判断参数是否为空
	 * @param information
	 * 				string类型参数，需要是否为空的变量
	 * @return Boolean
	 * 				false为空，true为非空
	 * */
	public Boolean judgeNull(String information){
		if(null == information.trim() || "".equals(information.trim())){
			return true;
		}else{
			return false;
		}
	}
}
