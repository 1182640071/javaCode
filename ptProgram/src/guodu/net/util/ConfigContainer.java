package guodu.net.util;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ConfigContainer{
	
	private static ConfigContainer instance;  								//本类唯一实例

	private static String url = "http://127.0.0.1:8000";	
	private static String messageUrl = "http://127.0.0.1:8000";
	

	
	static synchronized public ConfigContainer getInstance() {
		if (instance == null) {
			instance = new ConfigContainer();
		}
		return instance;
	}
	
	private ConfigContainer() {
		
	}
	
	
	/**
	 * 配置文件信息加载，根据关键词查询配置文件，如果没有所查找的关键词，则采用默认值
	 * */
     public void load(){
    	 System.out.println("[INFO]加载配置文件信息。。。");
    	 try {
			 Map<?,?> map = loadFunction("common");
			 url = getInfo("url","127.0.0.1", map);	 
			 messageUrl = getInfo("messageUrl","127.0.0.1", map);	
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("[ERROR]配置文件信息有误，请检查文件是否正常：");
		}
     }
     

     
     /**
     * 配置文件信息验证
     * 如果配置文件中已配置关键词的信息，则返回配置信息
     * 如果配置文件中没有配置关键词的信息，则返回默认值
     * @param e 关键词
     * @param defult 默认值
     * @param map
     * @return result
     * */
     public static String getInfo(String e , String  defult , Map<?,?> map)
     {
    	 String result = (String) map.get(e);
    	 if("".equals(result))
    	 {
    		 result = defult;
    	 }
    	 return result;
     }
     
     /**
      * 配置文件信息加载方法实现
      * @param e 节点
      * @param f 节点
      * @return map 
      * */
     public static Map<?, ?> loadFunction(String e)
     {
   	  SAXReader saxreader = new SAXReader();
   	  
   	  Document doc = null;
   	  Map<String,String> map = null;
		try {
			doc = saxreader.read(new File("config" + File.separator + "config.xml"));
			map = new HashMap<String,String>();
			if(null != e && !"".equals(e)){
				Element root = doc.getRootElement().element(e);
		    	for ( Iterator<?> iterInner = root.elementIterator(); iterInner.hasNext(); ) {   
		    		Element elementInner = (Element) iterInner.next();
		    	    map.put(elementInner.getName(), root.elementText(elementInner.getName()));
		    	}
			}
		} catch (DocumentException e1) {
			e1.printStackTrace();
			System.out.println("配置文件信息加载错误：");
		}
    	 return map;
     }
     
     public String toString(){
    	StringBuffer sb = new StringBuffer();
		sb.append("infomation:");
		sb.append(url).append("|!");
		return sb.toString();
	}

	public static String getMessageUrl() {
		return messageUrl;
	}

	public static void setMessageUrl(String messageUrl) {
		ConfigContainer.messageUrl = messageUrl;
	}

	public String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		ConfigContainer.url = url;
	}

	
	
	public static void main(String[] args){
    	 new ConfigContainer().load();
    }
}
