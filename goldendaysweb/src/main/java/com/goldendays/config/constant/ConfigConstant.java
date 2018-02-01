package com.goldendays.config.constant;

public class ConfigConstant {

	/**
	 * Reachable from browser, not refused by filter to authenticate,
	 * will be used for rest and source controller.
	 */
	
	public enum Directions{
		  LOGIN_SOURCE ("goldendaysweb/web/login"), LOGIN_REST ("goldendaysweb/rest/login");
		  
		private String url;
		
		Directions(String url) {
			this.url = url;
		}
		
		public String getUrl() {
			return url;
		}
	}
    public static String LOGIN_SOURCE_URL_SUFFIX = "goldendaysweb/web/login";
    public static String SINGUP_SOURCE_URL_SUFFIX = "goldendaysweb/web/signup";
    public static String LOGIN_REST_URL_SUFFIX = "goldendaysweb/rest/login";
    public static String SINGUP_REST_URL_SUFFIX = "goldendaysweb/rest/signup";

}
