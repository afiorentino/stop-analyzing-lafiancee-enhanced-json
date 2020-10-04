import org.json.*;
import java.io.*;
import java.util.*;

public class enhancedJson {
	public static void main(String args[]) {
		FileInputStream in;
		try {
			in = new FileInputStream("lafiancee.json");
			JSONObject obj = new JSONObject(new JSONTokener(in));
			JSONObject data = obj.getJSONObject("data");
			JSONObject catalog = data.getJSONObject("catalog");
			JSONObject category = catalog.getJSONObject("category");
			JSONObject prodMetaData = category.getJSONObject("productsWithMetaData");
			JSONArray list = prodMetaData.getJSONArray("list");
			/*for(int i = 0; i < list.length(); i++) {
				System.out.println(list.getJSONObject(i));
			}*/
			JSONObject sku = list.getJSONObject(0).getJSONObject("sku");
			System.out.println(sku);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
