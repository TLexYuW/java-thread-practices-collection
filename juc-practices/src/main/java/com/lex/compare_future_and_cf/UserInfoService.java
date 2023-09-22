package com.lex.compare_future_and_cf;

/**
 * @author : Lex Yu
 */
public class UserInfoService {
	public UserInfo getUserInfo(String userId) throws InterruptedException {
		Thread.sleep(500); //模擬呼叫耗時
		return new UserInfo("1", "Road", 50); //一般是查資料庫，或遠端呼叫回傳的
	}
}
