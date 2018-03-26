package cn.iiihunter.tencentAI.demo;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import cn.iiihunter.tencentAI.sign.TencentAISign;
import cn.iiihunter.tencentAI.util.Base64Util;
import cn.iiihunter.tencentAI.util.FileUtil;
import cn.iiihunter.tencentAI.util.HttpsUtil4Tencent;
import cn.iiihunter.tencentAI.util.TencentAPI;

public class FlowersDetectTest {
	/**
	 * 花卉检测
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		//获取时间戳
		String time_stamp = System.currentTimeMillis()/1000+"";
		
		//读取图片并进行编码
		byte [] imageData = FileUtil.readFileByBytes("E:/androidDev/images/yinghua.png");
		String img64 = Base64Util.encode(imageData);
		
		//获取随机字符串
		String nonce_str = TencentAISign.getRandomString(10);
		
		//计算签名
		String sign = TencentAISign.appSignAI4FlowersDetect(TencentAPI.APP_ID_AI, nonce_str,img64,2);
		
		//构造https请求
		Map<String, String> headers = new HashMap<String, String>();
		Map<String, String> bodys = new HashMap<String, String>();
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		bodys.put("app_id",TencentAPI.APP_ID_AI.toString());
		bodys.put("time_stamp",time_stamp);
		bodys.put("nonce_str", nonce_str);
		bodys.put("sign", sign);
		bodys.put("image", img64);
		bodys.put("scene", "2");//2 表示花卉识别
		
		HttpResponse responseBD = HttpsUtil4Tencent.doPostTencentAI(TencentAPI.FLOWERRECOURL, headers, bodys);
		System.out.println(EntityUtils.toString(responseBD.getEntity()));
	}
}
