package cn.espush;

import com.google.gson.annotations.SerializedName;

public class EspushMessages {
	@SerializedName("chipid")
	public String chipId;

	public String data;

	@SerializedName("created_at")
	public String createAt;
}
