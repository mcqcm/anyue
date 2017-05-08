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
	 * ���췽��.
	 * 
	 * @param datePattern
	 *            ���ڸ�ʽ
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
	 *            �����
	 * @param excludes
	 *            ��ת������������
	 * @param datePattern
	 *            date��stringת����ģʽ
	 * @throws Exception
	 *             д�����ݿ��ܳ����쳣
	 */
	public static String ObjectToJson(Object bean, String[] excludes,
			String datePattern) throws Exception {
		JsonConfig jsonConfig = configJson(excludes, datePattern);

		JSON json = JSONSerializer.toJSON(bean, jsonConfig);

		return json.toString();
	}

	/**
	 * ����json-lib��Ҫ��excludes��datePattern.
	 * 
	 * @param excludes
	 *            ����Ҫת������������
	 * @param datePattern
	 *            ����ת��ģʽ
	 * @return JsonConfig ����excludes��dataPattern���ɵ�jsonConfig������write
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
