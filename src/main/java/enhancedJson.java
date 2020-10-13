import org.json.*;
import java.io.*;
import java.util.*;
import java.net.*;

public class enhancedJson {
	public static void main(String args[]) {
		ArrayList <String> urlPartList = findURLPart();
		JSONArray enhancedArray = new JSONArray();
		for(int i = 0; i < urlPartList.size(); i++) {
			try {
				enhancedArray.put(uploadToServer(urlPartList.get(i)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("array", enhancedArray);
		try {
			FileWriter file = new FileWriter("enhancedJSON.json");
			file.write(jsonObject.toString());
			file.close();
		} catch (Exception ex) {
			ex.getMessage();
		}
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

	private static JSONObject uploadToServer(String urlPart) throws IOException, JSONException {
		String site = "https://www.lafiancee.com.br/_api/wix-ecommerce-storefront-web/api";
		String productID = urlPart;
		String json = "{" + 
    "\"credentials\": \"include\","+ 
    "\"headers\": { " +
        "\"Accept\": \"*/*\","+
        "\"Authorization\": \"brUTfgwc9eaqQ4m_KjbIkjnR-MRt9rGfCLGikGEPiRU.eyJpbnN0YW5jZUlkIjoiMWI0OTQ1ODItZDg5Zi00MmY2LTg0YzAtNTAxOGE3NzI1Y2MyIiwiYXBwRGVmSWQiOiIxMzgwYjcwMy1jZTgxLWZmMDUtZjExNS0zOTU3MWQ5NGRmY2QiLCJtZXRhU2l0ZUlkIjoiN2RlM2ExNjgtNDEyNC00NDljLTg4ZDYtZmViNjkzYWY3NzRjIiwic2lnbkRhdGUiOiIyMDIwLTA5LTIzVDEyOjI3OjE4LjUyOVoiLCJ2ZW5kb3JQcm9kdWN0SWQiOiJQcmVtaXVtMSIsImRlbW9Nb2RlIjpmYWxzZSwiYWlkIjoiOWE0ZjJjNDAtMTIzNC00ZGM3LTg3OWEtMjIzZDMxMzI0N2E1IiwiYmlUb2tlbiI6IjY2YWFlNGVhLTk5YmItMDY2YS0wYzE2LWFlYWUzNGRkMmI4ZSIsInNpdGVPd25lcklkIjoiZmI0Y2Y2ODQtODZkZS00N2E0LWE2NjUtZjE4ZDcxYzA3YzUxIn0\"," +
        "\"Content-Type\": \"application/json; charset=utf-8\"," +
    "}," +
    "\"body\": \'{\"query\":\"query getProductBySlug($externalId: String!, $slug: String!, $withPricePerUnit: Boolean!, $withCountryCodes: Boolean!) {" +
          "appSettings(externalId: $externalId) {" +
            "widgetSettings" +
      "}"+
      "catalog {" +
            "product(slug: $slug, onlyVisible: true) {" +
                "id" +
                "description" +
                "isVisible" + 
                "sku" +
                "ribbon" + 
		"price" +
                "comparePrice" +
                "discountedPrice" +
                "formattedPrice" +
                "formattedComparePrice" + 
                "formattedDiscountedPrice" +
                "pricePerUnit @include(if: $withPricePerUnit)" +
                "formattedPricePerUnit @include(if: $withPricePerUnit)" +
                "pricePerUnitData @include(if: $withPricePerUnit) {" +
                "baseQuantity" +
                "baseMeasurementUnit" +
          "}" +
          "seoTitle" +
          "seoDescription" +
          "createVersion" +
          "digitalProductFileItems {" +
                "fileId" +
                "fileType" +
                "fileName" +
          "}" +
          "productItems {" +
                "price" +
                "comparePrice" +
                "formattedPrice" +
                "formattedComparePrice" +
                "pricePerUnit @include(if: $withPricePerUnit)" +
                "formattedPricePerUnit @include(if: $withPricePerUnit)"  +
                "optionsSelections" +
                "isVisible" +
                "inventory {" +
                "status" +
                "quantity" +
            "}" +
            "sku" +
            "weight" +
            "surcharge" + 
	    "subscriptionPlans {" +
                "list {" +
                "id" +
                "price" +
                "formattedPrice" +
                "pricePerUnit @include(if: $withPricePerUnit)" +
                "formattedPricePerUnit @include(if: $withPricePerUnit)" +
              "}" +
            "}" +
          "}" +
          "name"  +
          "isTrackingInventory" +
          "inventory {" +
            "status" +
            "quantity" +
          "}" +
          "isVisible" +
          "isManageProductItems" +
          "isInStock" +
          "media {" +
            "id" +
            "url" +
            "fullUrl" +
            "altText" +
            "thumbnailFullUrl: fullUrl(width: 50, height: 50)" +
            "mediaType" +
            "videoType" +
            "videoFiles {" +
                "url" +
                "width" +
                "height" +
                "format" +
                "quality" +
            "}" +
            "width" +
            "height" +
            "index" +
            "title" +
          "}" +
          "customTextFields {" +
            "title" +
            "isMandatory" +
            "inputLimit" +
          "}" +
          "nextOptionsSelectionId" +
          "options {" +
            "title" +
            "optionType" +
            "selections {" +
                "id" +
                "value" +
                "description" +
                "linkedMediaItems {" +
                    "altText" +
                    "url" +
                    "fullUrl" +
                    "thumbnailFullUrl: fullUrl(width: 50, height: 50)" +
                    "mediaType" +
                    "width" +
                    "height" +
                    "index" +
                    "title" +
                    "videoFiles {" +
                        "url" +
                        "width" +
                        "height" +
                        "format" +
                        "quality" +
                    "}" +
                "}" +
            "}" +
          "}" +
          "productType" +
          "urlPart" +
          "additionalInfo {" +
                "id" +
            "title" +
            "description" +
            "index" +
          "}" +
          "subscriptionPlans {" +
                "list(onlyVisible: true) {" +
                  "id" +
              "name" +
              "tagline" +
              "frequency" +
              "duration" +
              "price" +
              "formattedPrice" +
              "pricePerUnit @include(if: $withPricePerUnit)" +
              "formattedPricePerUnit @include(if: $withPricePerUnit)" +
            "}" +
            "oneTimePurchase {" +
                  "index" +
            "}" +
          "}" +
          "discount {" +
                "mode" +
            "value" +
          "}" +
          "currency" +
          "weight" +
          "seoJson" +
        "}" +
      "}" +
      "localeData(language: \"en\") @include(if: $withCountryCodes) {" +
            "countries {" +
              "key" +
          "shortKey" +
        "}" +
      "}" +
    "}\",\"variables\":{\"slug\":"+ productID +",\"externalId\":\"\",\"withPricePerUnit\":true,\"withCountryCodes\":false},\"source\":\"WixStoresWebClient\",\"operationName\":\"getProductBySlug\"}\'," +
    "\"method\": \"POST\"" +
"}";
		URL url = new URL(site);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestMethod("POST");

		OutputStream os = conn.getOutputStream();
		os.write(json.getBytes("UTF-8"));
		os.close();

		InputStream in = new BufferedInputStream(conn.getInputStream());
		String result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
		JSONObject jsonObject = new JSONObject(result);

		in.close();
		conn.disconnect();
		return jsonObject;
	}
}
