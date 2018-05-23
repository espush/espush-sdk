package cn.espush;

import com.google.gson.annotations.SerializedName;

public class EspushDeviceDetail {
	public int id;

	@SerializedName("chipid")
	public String chipId;

	public String name;

	public String note;

	@SerializedName("create_at")
	public String createAt;

	//@SerializedName("extra_info")
	//public String extraInfo;
	
	public int rssi;
	
	@SerializedName("map")
	public int flashMap;
	
	public int adc;
	
	@SerializedName("app")
	public int appAddr;
	
	@SerializedName("flash")
	public int flashID;

	@SerializedName("heap")
	public int memory;

	public String firmware;

	public String version;
	
	@SerializedName("latest_ipaddr")
	public String latestIpaddr;

	public String stat;

	@SerializedName("up_datas")
	public String[] upDatas;

	@SerializedName("down_datas")
	public String[] downDatas;

	@SerializedName("app_name")
	public String appName;

	public String latest;

	public String alive;
}
