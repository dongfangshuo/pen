package com.pingan;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FlowData {
	private static final String FILEPATH = "";
	public static void main(String[] args) {
		FlowData flowData = new FlowData();
		FlowUnit[] units = flowData.getFlowDatas(FILEPATH);
		for(int i = 1,len = units.length; i < len; i++){
			if(units[i].getIn() == null || units[i].getOut() == null || units[i].getBalance() == null){
				process(i,units);
			}
		}
	}
	
	private static void process(int index,FlowUnit[] units) {
		FlowUnit flowUnit = units[index];
		
		
	}

	private  FlowUnit[] getFlowDatas(String file) {
		List<FlowUnit> datas = new ArrayList<FlowUnit>();
		BufferedReader reader = null;
		try {
			 reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			 String line = null;
			 int index = -1;
			 while((line = reader.readLine()) != null){
				 String[] els = line.split(";");
				 FlowUnit unit = new FlowUnit();
				 index = index == -1 ? Integer.parseInt(els[0]) : index;
				 unit.setNo(index ++);
				 unit.setIn(getFromStr(els[1]));
				 unit.setOut(getFromStr(els[2]));
				 unit.setBalance(getFromStr(els[3]));
				 datas.add(unit);
			 }
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
		return datas.toArray(new FlowUnit[0]);
	}
	/**
	 *  从字符串中解析出数据，如果是字符串“？”，则置为null
	 * @param string
	 * @return
	 */
	private BigDecimal getFromStr(String string) {
		if(string == null || string.trim().equalsIgnoreCase("?")){
			return null;
		}
		return BigDecimal.valueOf(Double.parseDouble(string));
	}

	private class FlowUnit{
		private long no;
		private BigDecimal out;
		private BigDecimal in;
		private BigDecimal balance;
		public long getNo() {
			return no;
		}
		public void setNo(long no) {
			this.no = no;
		}
		public BigDecimal getOut() {
			return out;
		}
		public void setOut(BigDecimal out) {
			this.out = out;
		}
		public BigDecimal getIn() {
			return in;
		}
		public void setIn(BigDecimal in) {
			this.in = in;
		}
		public BigDecimal getBalance() {
			return balance;
		}
		public void setBalance(BigDecimal balance) {
			this.balance = balance;
		}
		
		
		
	}
}
