package com.common;

import java.io.Writer;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;

public class DateJsonValueProcessor implements JsonValueProcessor {
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	private DateFormat dateFormat;

	/**
	 * 构造方法.
	 * 
	 * @param datePattern
	 *            日期格式
	 */
	public DateJsonValueProcessor(String datePattern) {
		try {
			dateFormat = new SimpleDateFormat(datePattern);
		} catch (Exception ex) {
			dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
		}
	}

	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return process(value);
	}

	public Object processObjectValue(String key, Object value,
			JsonConfig jsonConfig) {
		return process(value);
	}

	private Object process(Object value) {
		//return dateFormat.format((Date) value);
		if(value instanceof Timestamp)
			return dateFormat.format((Timestamp)value);
		else if(value instanceof Date)
			return dateFormat.format((Date)value);
		else if(value==null)
			return "";
		else
			return value.toString();
	}

	/**
	 * write.
	 * 
	 * @param bean
	 *            obj
	 * @param writer
	 *            输出流
	 * @param excludes
	 *            不转换的属性数组
	 * @param datePattern
	 *            date到string转换的模式
	 * @throws Exception
	 *             写入数据可能出现异常
	 */
	public static String ObjectToJson(Object bean, String[] excludes,
			String datePattern) throws Exception {
		JsonConfig jsonConfig = configJson(excludes, datePattern);

		JSON json = JSONSerializer.toJSON(bean, jsonConfig);

		return json.toString();
	}

	/**
	 * 配置json-lib需要的excludes和datePattern.
	 * 
	 * @param excludes
	 *            不需要转换的属性数组
	 * @param datePattern
	 *            日期转换模式
	 * @return JsonConfig 根据excludes和dataPattern生成的jsonConfig，用于write
	 */
	public static JsonConfig configJson(String[] excludes, String datePattern) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor(datePattern));
		return jsonConfig;
	}
}
