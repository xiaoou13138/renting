package com.ncu.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * @author oulc
 *
 */
public class StringUtil {
	/**
	 * 把带下划线的单词组变成驼峰式的单词组
	 * @param word
	 * @param firstUp 首字母是否是大写 true是首字符大写 反之
	 * @return
	 */
	public static String toHumpWord(String word,boolean firstUp){
		String [] words = word.split("_");
		StringBuilder str = new StringBuilder();
		int length = words.length;
		for(int i =0;i<length ;i++){
			if(i == 0){
				if(firstUp == true){
					str.append(words[i].substring(0,1).toUpperCase()+words[i].substring(1).toLowerCase());
				}else{
					str.append(words[i].toLowerCase());
				}
			}else{
				str.append(words[i].substring(0,1).toUpperCase()+words[i].substring(1).toLowerCase());
			}
		}
		return str.toString();
	}
	
	/**
	 * 把单词转换成带下划线的单词(大写)
	 * @param word
	 * @return
	 */
	public static String toUnderLineWord(String word){
		Pattern pattern = Pattern.compile("[A-Z]");
		Matcher matcher = pattern.matcher(word);
		StringBuilder str = new StringBuilder();
		int begin = 0;
		while(matcher.find()){
			str.append(word.substring(begin, matcher.start()).toUpperCase()).append("_");
			begin = matcher.start();
		}
		str.append(word.substring(begin, word.length()).toUpperCase());
		return str.toString();
		
	}
}
