package com.cgyue.t1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class T2 {
	public static void main(String[] args) {
		// String result = "";
		try {
			String result = "";
			URL httpurl = new URL("http://xui.ptlogin2.qq.com/cgi-bin/qlogin");
			HttpURLConnection httpConn = (HttpURLConnection) httpurl.openConnection();
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += "\n"+line;
			}
			in.close();
			System.out.println(result);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

}
