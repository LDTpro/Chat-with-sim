package hust.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class APISim {
	public static void sendPost(String text) throws Exception {
		URL obj = new URL("https://sim.vuiz.net/post_sim.php");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");

		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(("hoi=" + text + "&lang=vn").getBytes());
		os.flush();
		os.close();

		int responseCode = con.getResponseCode();

		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			parseJSon(response.toString());
		} else {
			System.out.println("POST request not worked");
		}
	}

	public static void parseJSon(String s) {
		JSONObject jsonObject = new JSONObject(s);
		if ((Integer) jsonObject.get("error") == 0) {
			System.out.println(jsonObject.getString("message").replaceAll("\n", ""));
		}

	}
}
