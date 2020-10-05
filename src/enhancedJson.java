import org.json.*;
import java.io.*;
import java.util.*;

public class enhancedJson {
	public static void main(String args[]) {
		ArrayList <String> urlPartList = findURLPart();
		System.out.println(urlPartList);
		saveURLPart(urlPartList);
	}

	public static ArrayList <String> findURLPart() {
		FileInputStream in;
		ArrayList<String> urlPartList = new ArrayList<String>();
		try {
			in = new FileInputStream("lafiancee.json");
			JSONObject obj = new JSONObject(new JSONTokener(in));
			JSONArray list = obj.getJSONObject("data").getJSONObject("catalog").getJSONObject("category").getJSONObject("productsWithMetaData").getJSONArray("list");
			for(int i = 0; i < list.length(); i++) {
				urlPartList.add(list.getJSONObject(i).getString("urlPart"));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return urlPartList;
	}

	public static void saveURLPart(ArrayList<String> urlPartList) {
		try {
			FileWriter file = new FileWriter("urlPart.json");
			file.write(toJSONString(urlPartList));
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String toJSONString(ArrayList<String> urlPartList) {
		String ret;
		JSONObject obj = new JSONObject();
		for(int i = 0; i < urlPartList.size(); i++) {
			obj.put("urlPart", urlPartList.get(i));
		}
		ret = obj.toString();
		return ret;
	}

			
}
