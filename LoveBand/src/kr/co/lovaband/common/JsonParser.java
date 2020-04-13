package kr.co.lovaband.common;

import java.io.BufferedReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonParser {
	
	public JSONObject jsonParser(BufferedReader read) {
		//안드로이드에서 byte타입 json으로 넘어온 값을 BufferedReader로 읽은 후 
		BufferedReader reader = read;
		JSONParser parser;
		
		//그 값을 String으로 변환하기 위한 StringBuffer 선언
		StringBuffer sb = new StringBuffer();
		
		String line = null;
		JSONObject jsondata = null;
//		JSONArray jsonarray = null;
		
		try {
			while ( (line = reader.readLine()) != null ) {
			//한줄씩읽어서 담아줌
			sb.append(line);
			}
			//while문에서 StringBuffer 타입으로 리턴된 값들을 toString으로 String으로 변환
			String userData = sb.toString();
			System.out.println(sb.toString() + " >>> sb.toString");
			System.out.println(userData + " >>> userData");
			
			
			parser = new JSONParser();
			Object object = parser.parse(userData);
			
//			jsonarray = (JSONArray)object;
//			jsondata = (JSONObject) jsonarray.get(0);
			
			jsondata = (JSONObject)object;
		} catch (Exception e) {
			System.out.println(e.getMessage() + " json파싱 에러");
		}
		
		/**************** 안드로이드에서 byte 타입으로 넘어온 json 데이터를 stringBuffer -> jsonObect -> String 으로 파싱 하는 과정 ***************/
				
		return jsondata;		
	}
}
