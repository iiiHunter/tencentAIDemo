package cn.iiihunter.tencentAI.sign;

import java.net.URLEncoder;
import java.util.Random;

import cn.iiihunter.tencentAI.util.TencentAPI;

public class TencentAISign {

	public static String appSignAI4FlowersDetect(Integer appId,String nonce_str,String image,Integer scene) throws Exception {
		return appSignBaseAI4FlowersDetect(appId,nonce_str,image,scene);
	}

	private static String appSignBaseAI4FlowersDetect(Integer appId,
			String nonce_str,String image,Integer scene) throws Exception {
		String time_stamp = System.currentTimeMillis()/1000 + "";
		
		//按照键值字典升序，“键值=值”形式构建字符串
		String plain_text = "app_id=" + URLEncoder.encode(appId.toString(),"UTF-8")
				+ "&image=" + URLEncoder.encode(image,"UTF-8")
				+"&nonce_str=" + URLEncoder.encode(nonce_str,"UTF-8")
				+"&scene=" + URLEncoder.encode(scene.toString(),"UTF-8")
				+ "&time_stamp=" + URLEncoder.encode(time_stamp,"UTF-8");
        String plain_text_encode = plain_text+"&app_key="+TencentAPI.APP_KEY_AI;
        String sign = MD5.getMD5(plain_text_encode);
        //System.out.println("sign: "+sign);
		return sign;
	}

	public static String getRandomString(int length) {
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 }
}
