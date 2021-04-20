package com.youzikj.pay.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 签名、验签工具类
 */
public final class VerifyUtils {

	/**
	 * 请求订单签名（微信H5、微信公众号、支付宝H5）
	 * @param amount 金额
	 * @param channel 商户ID
	 * @param subject 商品名称
	 * @param url 支付成功后跳回的地址
	 * @param key 商户密钥
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String sign(Integer amount, Integer channel, String subject, String url, String key) throws NoSuchAlgorithmException {
		StringBuilder sb = new StringBuilder()
		.append("amount=").append(amount).append("&")
		.append("channel=").append(channel).append("&")
		.append("subject=").append(subject).append("&")
		.append("url=").append(url).append("&")
		.append("key=").append(key);
		return md5(sb.toString());
	}

	/**
	 * 请求订单签名（微信APP、支付宝APP）
	 * @param amount 金额
	 * @param channel 商户ID
	 * @param subject 商品名称
	 * @param url 支付成功后跳回的地址
	 * @param key 商户密钥
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String sign(Integer amount, Integer channel, String subject, String key) throws NoSuchAlgorithmException {
		StringBuilder sb = new StringBuilder()
		.append("amount=").append(amount).append("&")
		.append("channel=").append(channel).append("&")
		.append("subject=").append(subject).append("&")
		.append("key=").append(key);
		return md5(sb.toString());
	}
	
	/**
	 * 同步订单验签
	 * @param trade_no 订单号
	 * @param price 金额
	 * @param state 状态
	 * @param order_time 时间
	 * @param order_no 官方订单号
	 * @param ext 透传参数
	 * @param sign 签名串
	 * @param key 商户密钥
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public static boolean check(String trade_no, String price, String state, String order_time, String order_no, String ext, String sign, String key) throws NoSuchAlgorithmException {
		StringBuilder sb = new StringBuilder()
		.append("ext=").append(ext).append("&")
		.append("order_no=").append(order_no).append("&")
		.append("order_time=").append(order_time).append("&")
		.append("price=").append(price).append("&")
		.append("state=").append(state).append("&")
		.append("trade_no=").append(trade_no).append("&")
		.append("key=").append(key);
		if (sign.equalsIgnoreCase(md5(sb.toString()))) {
			return true;
		}
		return false;
	}
	
	/**
	 * md5签名
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String md5(String str) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(str.getBytes());
		byte b[] = md.digest();
		int i;
		StringBuilder buf = new StringBuilder();
		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0)
				i += 256;
			if (i < 16)
				buf.append("0");
			buf.append(Integer.toHexString(i));
		}
		return buf.toString();
	}
	
}
