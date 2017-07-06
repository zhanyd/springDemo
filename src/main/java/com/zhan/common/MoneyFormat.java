package com.zhan.common;

public class MoneyFormat {

	/**
	 * 金额小写转大写
	 * @param price
	 * @return
	 */
	public static String amountInWords(String price) {
        String unit = "千百拾亿千百拾万千百拾元角分", str = "";
        price += "00";
        int p = price.indexOf('.');
        if (p >= 0){
        	price = price.substring(0, p) + price.substring(p+1, p+3);
        }
        unit = unit.substring(unit.length() - price.length());
        for (int i=0; i < price.length(); i++){
        	str += String.valueOf("零壹贰叁肆伍陆柒捌玖".charAt(Integer.parseInt(String.valueOf(price.charAt(i))))) + String.valueOf(unit.charAt(i));
        }
        
        return str.replaceAll("零[千|百|拾|角]", "零").replaceAll("零+", "零").replaceAll("零(万|亿|元)", "$1").replaceAll("(亿)万", "$1").replaceAll("^元零?|零分", "").replaceAll("元$", "元整");
	}
        
}
