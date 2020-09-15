package com.linkknown.io;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsumerRecord {

	// 用户 id
	private String userId;
	// 消费时间
	private Date consumerDate;
	// 消费金额
	private double consumerAmount;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getConsumerDate() {
		return consumerDate;
	}

	public void setConsumerDate(Date consumerDate) {
		this.consumerDate = consumerDate;
	}

	public double getConsumerAmount() {
		return consumerAmount;
	}

	public void setConsumerAmount(double consumerAmount) {
		this.consumerAmount = consumerAmount;
	}

	@Override
	public String toString() {
		return "ConsumerRecord [userId=" + userId + ", consumerDate=" + consumerDate + ", consumerAmount="
				+ consumerAmount + "]";
	}

	
	public static List<ConsumerRecord> getRandomInstance (int count) {
		List<ConsumerRecord> lst = new ArrayList<ConsumerRecord>();

		for (int i=0; i< count; i++) {
			ConsumerRecord consumerRecord = new ConsumerRecord();
			// 0 代表前面补充0
			// 10 代表长度为10
			// d 代表参数为正数型
			consumerRecord.setUserId(String.format("%010d", i));		// 0000000001
			consumerRecord.setConsumerDate(new Date());
			consumerRecord.setConsumerAmount(i);
			
			lst.add(consumerRecord);
		}
		return lst;
	}
}
