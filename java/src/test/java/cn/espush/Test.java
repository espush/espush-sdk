package cn.espush;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import junit.framework.TestCase;

public class Test extends TestCase {

	/*public void testListDevices() {
		String baseAddr = "http://localhost:8000";
		String key = "7372434262817581";
		String pswd = "f35cb1d1834d74826c104bb7359e2db4";
		EspushSdk sdk = new EspushSdk();
		sdk.SetAddr(baseAddr);
		sdk.SetAppInfo(key, pswd);
		DevicesPaginator devicesPaginator = null;
		try {
			devicesPaginator = sdk.ListDevices(1, 10);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Util.reflect(devicesPaginator);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testDeviceDetail() {
		String baseAddr = "http://localhost:8000";
		String key = "7372434262817581";
		String pswd = "f35cb1d1834d74826c104bb7359e2db4";
		EspushSdk sdk = new EspushSdk();
		sdk.SetAddr(baseAddr);
		sdk.SetAppInfo(key, pswd);
		EspushDeviceDetail deviceDetail = null;
		try {
			deviceDetail = sdk.DeviceDetail(1);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Util.reflect(deviceDetail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testUpdateDevice() {
		String baseAddr = "http://localhost:8000";
		String key = "7372434262817581";
		String pswd = "f35cb1d1834d74826c104bb7359e2db4";
		EspushSdk sdk = new EspushSdk();
		sdk.SetAddr(baseAddr);
		sdk.SetAppInfo(key, pswd);
		try {
			sdk.UpdateDevice(2, "测试22", "name note22", "VALID");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

	public void testFindMessagesByApp() {
		String baseAddr = "http://localhost:8000";
		String key = "7372434262817581";
		String pswd = "f35cb1d1834d74826c104bb7359e2db4";
		EspushSdk sdk = new EspushSdk();
		sdk.SetAddr(baseAddr);
		sdk.SetAppInfo(key, pswd);
		MessagesPaginator mp = null;
		try {
			mp = sdk.FindMessagesByApp(1, 10);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Util.reflect2(mp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*public void testSendMessages() {
		String baseAddr = "http://localhost:8000";
		String key = "7372434262817581";
		String pswd = "f35cb1d1834d74826c104bb7359e2db4";
		EspushSdk sdk = new EspushSdk();
		sdk.SetAddr(baseAddr);
		sdk.SetAppInfo(key, pswd);
		JsonResponse jrsp = null;
		try {
			jrsp=sdk.SendMessages(1, "ASCII", 11, "AABBaabb");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Util.reflect(jrsp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
