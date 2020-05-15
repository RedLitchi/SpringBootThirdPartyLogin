package com.nieqiang.webapp.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nieqiang.webapp.vo.QQSDK;

@RestController
@RequestMapping("/q")
public class QQController {
	QQSDK qQSDK = new QQSDK();
	
	@Autowired(required=true)
	private RestTemplate restTemplate;
	
	@GetMapping("getCode")
	public void getCode(HttpServletResponse response, HttpServletRequest request) throws IOException{
		String getCode = qQSDK.getCodeCallbackURL()//获取code码地址
				+"?client_id="//appid
				+ qQSDK.getAppID()
				+ "&state="//这个说是防攻击的，就给个随机uuid吧
				+UUID.randomUUID()
				+"&redirect_uri="//这个很重要，这个是回调地址，即就收腾讯返回的code码
				+qQSDK.getRedirectURL()
				+"&response_type=code";//授权模式，授权码模式
		response.sendRedirect(getCode);
	}
	
	/**
	 * 用getCode接口获取到的Code跳转到这里，用Code换取AccessToken
	 * @param code
	 * @return
	 */
	@GetMapping("login")
	public String login(String code){
		//请求AccessToken的URL地址
		String getAccessTokenURL = qQSDK.getAccessTokenCallback()+"?grant_type=authorization_code&client_id="
				+qQSDK.getAppID()+"&client_secret="+qQSDK.getAppKey()+"&redirect_uri="+qQSDK.getRedirectURL()+"&code="+code;
		
		//发送GET请求并且带上Code => 获取AccessToken
		String resultAccessToken = restTemplate.getForObject(getAccessTokenURL,String.class);
		
		String userinfo = null;
		try {
			//在返回的值中截取AccessToken
			String accessToken = resultAccessToken.substring(resultAccessToken.indexOf("=")+1, resultAccessToken.indexOf("&"));
			
			//获取OpenID的URL地址
			String getOpenIdURL = qQSDK.getOpenIDCallbackURL()+"?access_token="+accessToken;
			
			//发送GET请求并且带上AccessToken => 获取OpenID
			String resultOpenId = restTemplate.getForObject(getOpenIdURL,String.class);
			
			//在返回的值中截取OpenID
			String openId =  resultOpenId.substring(resultOpenId.lastIndexOf("\"",resultOpenId.lastIndexOf("\"")-1)+1,resultOpenId.lastIndexOf("\""));
			
			//发送GET请求并且带上OpenID => 获取用户信息
			userinfo = restTemplate.getForObject(qQSDK.getUserinfoCallbackURL()+"?access_token=" + accessToken+
					"&oauth_consumer_key="+qQSDK.getAppID()+"&openid="+openId, String.class);
			
			System.out.println("请求token时返回的内容："+resultAccessToken+"\n截取到的token："+accessToken);
			System.out.println("请求openId时返回的内容："+resultOpenId+"\n截取到的openId："+openId);
		}catch(Exception e) {
			return "请重新授权！";
		}
//		return "请求token时返回的内容："+resultAccessToken+"\n截取到的token："+accessToken+"\n"
//				+"请求openId时返回的内容："+resultOpenId+"\n截取到的openId："+openId+">>>>>\n"+
		return userinfo;
	}
}