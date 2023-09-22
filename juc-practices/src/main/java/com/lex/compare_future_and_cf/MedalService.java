package com.lex.compare_future_and_cf;

/**
 * @author : Lex Yu
 */
public class MedalService {
	public MedalInfo getMedalInfo(String uuid) throws InterruptedException {
		Thread.sleep(500); //模擬呼叫耗時
		return new MedalInfo("123-456-789", "Guard");
	}
}
