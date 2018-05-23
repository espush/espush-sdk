package cn.espush;

import com.google.gson.annotations.SerializedName;

public class EspushDevice {
	public int id;
	
	@SerializedName("chipid")
	public String chipId;
	
	public String name;
	
	public String note;
	
	@SerializedName("create_at")
	public String createAt;
	
	public String latest;
	
	public String stat;
	
	public boolean online;
	
	public String regist;
	
	public String addr;

	public int msgId;
}
