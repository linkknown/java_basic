package com.linkknown.io;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsumerRecord {

	// �û� id
	private String userId;
	// ����ʱ��
	private Date consumerDate;
	// ���ѽ��
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
			// 0 ����ǰ�油��0
			// 10 ������Ϊ10
			// d �������Ϊ������
			consumerRecord.setUserId(String.format("%010d", i));		// 0000000001
			consumerRecord.setConsumerDate(new Date());
			consumerRecord.setConsumerAmount(i);
			
			lst.add(consumerRecord);
		}
		return lst;
	}
}
