package com.zive.util;

public enum WechatAPI {
	TEMPLATE_MESSAGE("/cgi-bin/message/template/send"),
	ACCESS_TOKEN("/cgi-bin/token"),
	GET_ALL_TEMPLATE("/cgi-bin/template/get_all_private_template"),
	DEL_TEMPLATE("/cgi-bin/template/del_private_template"),
	SET_INFUSTRY("/cgi-bin/template/api_set_industry"),
	GET_INFUSTRY("/cgi-bin/template/get_industry"),
	GET_OPENID("/sns/oauth2/access_token"),
	GET_Ticket("/cgi-bin/ticket/getticket");
    private String url;
	private WechatAPI(String url) {
		// TODO Auto-generated constructor stub
		this.url = WechatUtil.ROOT_URL + url;
	}
	public String value() {
		return this.url;
	}
}
