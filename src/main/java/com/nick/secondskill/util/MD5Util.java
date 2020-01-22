package com.nick.secondskill.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5
 *
 * 实际上做MD5也不是绝对安全的，但是我们可以使得破解的难度指数级增长。md5是不可逆的，不能反向解密的，
 * 网上所谓的“解密”都是把“加密”结果存储到数据库再比对的只能暴力破解，即有一个字典，从字典中读取一条记录，
 * 将密码用加salt盐值做MD5来对比数据库里面的值是否相等。
 * 因为好事者收集常用的密码，然后对他们执行 MD5，然后做成一个数据量非常庞大的数据字典，
 * 然后对泄露的数据库中的密码进行对比，如果你的原始密码很不幸的被包含在这个数据字典中，
 * 那么花不了多长时间就能把你的原始密码匹配出来，这个数据字典很容易收集，
 * 假设有600w个密码。坏人们可以利用他们数据字典中的密码，加上我们泄露数据库中的 Salt，然后散列，然后再匹配。
 * 但是由于我们的 Salt 是随机产生的，每条数据都要加上 Salt 后再散列，
 * 假如我们的用户数据表中有 30w 条数据，数据字典中有 600w 条数据，
 * 坏人们如果想要完全覆盖的话，他们就必须加上 Salt 后再散列的数据字典数据量就应该是 300000* 6000000 = 1800000000000，
 * 所以说干坏事的成本太高了吧。但是如果只是想破解某个用户的密码的话，只需为这 600w 条数据加上 Salt，然后散列匹配。
 * 可见 Salt 虽然大大提高了安全系数，但也并非绝对安全。
 * 实际项目中，Salt 不一定要加在最前面或最后面，也可以插在中间嘛，也可以分开插入，
 * 也可以倒序，程序设计时可以灵活调整，都可以使破解的难度指数级增长。
 * @author 17996
 *
 */
public class MD5Util {
	public static String md5(String src) {
		return DigestUtils.md5Hex(src);
	}
	//客户端固定的salt，跟用户的密码做一个拼装
	private static final String salt="1a2b3c4d";
	
	public static String inputPassToFormPass(String inputPass) {
		String str=""+salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(4);
		System.out.println(md5(str));
		return md5(str); 			//char类型计算会自动转换为int类型
	}
	//二次MD5
	public static String formPassToDBPass(String formPass,String salt) {//随机的salt
		String str=""+salt.charAt(0)+salt.charAt(2)+formPass+salt.charAt(5)+salt.charAt(4);
		return md5(str); 			 
	}
	//数据库md5,使用数据库随机salt,这个随机salt要在客户端登录的时候随机生成，并保存在数据库中
	public static String inputPassToDbPass(String input,String saltDB) {
		String formPass=inputPassToFormPass(input);
		System.out.println(formPass);
		String dbPass=formPassToDBPass(formPass,saltDB);
		return dbPass;
	}
	
	public static void main(String[] args) {
//		System.out.println(inputPassToFormPass("123456"));
//		String salt="1a2b3c4d";
//		System.out.println(formPassToDBPass(inputPassToFormPass("123456"),salt));
		System.out.println(inputPassToDbPass("111111",salt));
		
		System.out.println(formPassToDBPass("d018506bc314a32b93eb214102399a63",salt));
		//d018506bc314a32b93eb214102399a63
	}
}
