package com.jdbc.my_mongo;

import java.io.IOException;

import com.google.gson.Gson;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiLearning {
	
	private static OkHttpClient client;
	private static Response response;
	private static Request request;
	private static Call call;
	private static Gson gson = new Gson();
	
	public static void callApi() {
		
		client = new OkHttpClient();
		
		request = new Request.Builder()
				.url("https://randomuser.me/api/")
				.build();
		
		call = client.newCall(request);
		
		call.enqueue(new Callback() {
			
			@Override
			public void onResponse(Call call, Response response) throws IOException {
				
				String json = response.body().string();
				
				// gson library is used to convert json string into java object
				
				Root root =  gson.fromJson(json,Root.class);
				
				System.out.println(root.results.get(0).name.title);
				System.out.println(root.results.get(0).name.first);
				System.out.println(root.results.get(0).name.last);
				System.out.println(root.results.get(0).registered.age);
				System.out.println(root.results.get(0).location.postcode);
				
			}
			
			@Override
			public void onFailure(Call call, IOException e) {
				
				System.out.println(e.getMessage());
			}
		});
		
	}

	public static void main(String[] args) {
		
		callApi();
	}

}
