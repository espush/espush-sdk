package cn.espush;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

import org.apache.commons.beanutils.PropertyUtilsBean;

public class Util {
	public static void reflect(Object model) throws Exception {
		Field[] field = model.getClass().getDeclaredFields();
		for (int j = 0; j < field.length; j++) {
			System.out.println(field[j].getName() + ":" + field[j].get(model));
		}
	}

	public static void reflect2(Object model) {
		try {
			PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
			PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(model);
			for (int i = 0; i < descriptors.length; i++) {
				String name = descriptors[i].getName();
				//System.out.println(name);
				//if (!"class".equals(name)) {
					System.out.println(name + ":" + propertyUtilsBean.getNestedProperty(model, name));
				//}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}