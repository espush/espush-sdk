package cn.espush;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.http.HttpMethod;

public class EspushSdk {
	private String BaseAddr = "";
	private String Key = "";
	private String Pswd = "";
	private final String BASE_PATH = "/api/server";
	private static final MediaType TYPE_JSON = MediaType.parse("text/json; charset=utf-8");

	public void SetAddr(String addr) {
		this.BaseAddr = addr;
	}

	public void SetAppInfo(String key, String pswd) {
		this.Key = key;
		this.Pswd = pswd;
	}

	private static String md5str(String ori) throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance("md5");
		md5.update(ori.getBytes());
		BigInteger bigInt = new BigInteger(1, md5.digest());
		return bigInt.toString(16);
	}

	private static String calcSign(String urlpath, String method, String key, String password, long timestamp)
			throws NoSuchAlgorithmException {
		String M = method;
		String P = urlpath;
		String C = "key=" + key + "timestamp=" + timestamp;
		String K = password;
		String S = new String(M + P + C + K).toLowerCase();
		String R = EspushSdk.md5str(S);
		return R.toLowerCase();
	}

	private String commonRequest(String uri, String method, Map<String, String> args, String body)
			throws IOException, NoSuchAlgorithmException {
		if (!uri.startsWith("/")) {
			uri = "/" + uri;
		}
		long timestamp = System.currentTimeMillis() / 1000;
		String fulluri = this.BaseAddr + this.BASE_PATH + uri;
		String urlpath = this.BASE_PATH + uri;
		if (args == null) {
			args = new HashMap<String, String>();
		}
		String signStr = EspushSdk.calcSign(urlpath, method, this.Key, this.Pswd, timestamp);
		OkHttpClient client = new OkHttpClient();
		HttpUrl.Builder builder = HttpUrl.parse(fulluri).newBuilder();
		for (Map.Entry<String, String> entry : args.entrySet()) {
			builder.addQueryParameter(entry.getKey(), entry.getValue());
		}
		fulluri = builder.build().toString();
		Request.Builder reqBuilder = new Request.Builder().url(fulluri);

		Request req;
		if (HttpMethod.requiresRequestBody(method)) {
			req = reqBuilder.method(method, RequestBody.create(TYPE_JSON, body)).header("Key", this.Key)
					.addHeader("Timestamp", Long.toString(timestamp)).addHeader("Sign", signStr).build();
		} else {
			req = reqBuilder.method(method, null).header("Key", this.Key)
					.addHeader("Timestamp", Long.toString(timestamp)).addHeader("Sign", signStr).build();
		}
		Response rsp = client.newCall(req).execute();
		String str = rsp.body().string();
		System.out.println("str:" + str);
		return str;
	}

	public DevicesPaginator ListDevices(int page, int count) throws NoSuchAlgorithmException, IOException {
		String uri = "/devices";
		String method = "GET";
		if (page < 0 || count < 0) {
			throw new ArithmeticException();
		}
		Map<String, String> args = new HashMap<String, String>();
		args.put("page", Integer.toString(page));
		args.put("count", Integer.toString(count));
		args.put("filter", "all");
		String rspbody = this.commonRequest(uri, method, args, "");

		Gson gson = new Gson();
		return gson.fromJson(rspbody, DevicesPaginator.class);
	}

	public EspushDeviceDetail DeviceDetail(int devdbid) throws NoSuchAlgorithmException, IOException {
		String uri = "/devices/" + Integer.toString(devdbid);
		String method = "GET";
		String rspbody = this.commonRequest(uri, method, null, "");
		Gson gson = new Gson();
		return gson.fromJson(rspbody, EspushDeviceDetail.class);
	}

	private class _updateDeviceReq {
		@SuppressWarnings("unused")
		public String name;
		@SuppressWarnings("unused")
		public String note;
		@SuppressWarnings("unused")
		public String stat;
	}

	public JsonResponse UpdateDevice(int devdbid, String name, String note, String stat)
			throws NoSuchAlgorithmException, IOException {
		String uri = "/devices/" + Integer.toString(devdbid);
		String method = "PUT";
		Gson gson = new Gson();
		_updateDeviceReq req = new _updateDeviceReq();
		req.name = name;
		req.note = note;
		req.stat = stat;
		String reqbody = gson.toJson(req);
		String rspbody = this.commonRequest(uri, method, null, reqbody);
		System.out.println(rspbody);
		return gson.fromJson(rspbody, JsonResponse.class);
	}

	public MessagesPaginator FindMessagesByApp(int page, int count) throws NoSuchAlgorithmException, IOException {
		String uri = "/up/messages";
		String method = "GET";
		if (page < 0 || count < 0) {
			throw new ArithmeticException();
		}
		Map<String, String> args = new HashMap<String, String>();
		args.put("page", Integer.toString(page));
		args.put("count", Integer.toString(count));
		String rspbody = this.commonRequest(uri, method, args, "");
		Gson gson = new Gson();
		return gson.fromJson(rspbody, MessagesPaginator.class);
	}

	private class _sendMessagesReq {
		@SerializedName("data_type")
		public String dataType;
		@SerializedName("msgtype")
		public int msgType;
		@SuppressWarnings("unused")
		public String data;
	}

	public JsonResponse SendMessages(int devdbid, String dataType, int msgType, String data)
			throws NoSuchAlgorithmException, IOException {
		String uri = "/device/" + Integer.toString(devdbid) + "/messages";
		String method = "POST";
		_sendMessagesReq req = new _sendMessagesReq();
		req.dataType = dataType;
		req.msgType = msgType;
		req.data = data;
		Gson gson = new Gson();
		String reqbody = gson.toJson(req);
		String rspbody = this.commonRequest(uri, method, null, reqbody);
		return gson.fromJson(rspbody, JsonResponse.class);
	}
}
