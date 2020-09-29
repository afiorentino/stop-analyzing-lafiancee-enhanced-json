import org.json.JSONString;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;


public class enhancedJson {
	public static void main(String args[]){
		FileInputStream in = new FileInputStream("lafiancee.json");
		JSONObject obj = new JSONObject(new JSONTokener(in));
		System.out.println(obj.get("urlPart"));
	}
}
