package com.nieqiang.webapp.vo;

public class QQSDK {
	private String appID = "101877394";//https://connect.qq.com/manage.html#/appinfo/web/101877394
	private String appKey = "s";//https://connect.qq.com/manage.html#/appinfo/web/101877394
	private String redirectURL = "http://nieqiang.xyz/q/login";//你接收响应code码地址(创建应用时填写的"网站回调域")
	private String codeCallbackURL = "https://graph.qq.com/oauth2.0/authorize";//腾讯获取code码地址
	private String accessTokenCallback = "https://graph.qq.com/oauth2.0/token";//腾讯获取access_token地址
	private String openIDCallbackURL = "https://graph.qq.com/oauth2.0/me";//腾讯获取openid地址
	private String userinfoCallbackURL = "https://graph.qq.com/user/get_user_info";//腾讯获取用户信息地址
	
	/**
	 * appID
	 * @return
	 */
	public String getAppID() {
		return appID;
	}
	
	/**
	 * appKey
	 * @return
	 */
	public String getAppKey() {
		return appKey;
	}
	
	/**
	 * 你接收响应code码地址
	 * @return
	 */
	public String getRedirectURL() {
		return redirectURL;
	}
	
	/**
	 * 腾讯获取code码地址
	 * @return
	 */
	public String getCodeCallbackURL() {
		return codeCallbackURL;
	}
	
	/**
	 * 腾讯获取access_token地址
	 * @return
	 */
	public String getAccessTokenCallback() {
		return accessTokenCallback;
	}
	
	/**
	 * 腾讯获取openid地址
	 * @return
	 */
	public String getOpenIDCallbackURL() {
		return openIDCallbackURL;
	}
	
	/**
	 * 腾讯获取用户信息地址
	 * @return
	 */
	public String getUserinfoCallbackURL() {
		return userinfoCallbackURL;
	}
}