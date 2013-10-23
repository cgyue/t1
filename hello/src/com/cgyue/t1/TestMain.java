package com.cgyue.t1;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import com.cgyue.bean.Person;

public class TestMain {
	/**
	 * 设置属性
	 */
	public void test1() throws Exception{
		
		Person person = new Person();
		
		BeanUtils.setProperty(person, "username", "cgyue");
		BeanUtils.setProperty(person, "email", "yue1132@gmail.com");
		
		String s = BeanUtils.getProperty(person, "username");
		String s1 = BeanUtils.getProperty(person, "email");
		
		System.out.println("username: "+s);
		System.out.println("email: "+s1);
	}
	/**
	 * 自定义转换器
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public void test2() throws Exception{
		Person p = new Person();
		
		ConvertUtils.register(new Converter() {	
			public Object convert(Class type, Object value) {
				if(value == null){
					return null;
				}else if(!(value instanceof String)){
					new ConversionException("ConversionException");
				}
				
				String str = (String)value;
				if(str.trim().equals("")){
					return null;
				}
				
				SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
				try {
					return sdf.parse(str);
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}		
			}
		}, Date.class);
		
		BeanUtils.setProperty(p, "birth", "1987-11-15");
//		System.out.println(p.getBirth().getClass().getName());
		System.out.println(BeanUtils.getProperty(p, "birth").getClass().getName());
	}
	
	public void test3() throws Exception{
		Person p = new Person();
		ConvertUtils.register(new DateLocaleConverter(), Date.class);
		BeanUtils.setProperty(p, "birth", "1987-11-15");
	}
	
	public static void main(String[] args) throws Exception {
		TestMain t = new TestMain();
//		t.test1();
//		t.test2();
		t.test3();
	}
}
