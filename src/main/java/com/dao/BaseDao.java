package com.dao;


import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

/**
 * @author Administrator

 */
public interface BaseDao {

	/**
	 * 閸旂姾娴囬崚璺虹暰ID閻ㄥ嫭瀵旀稊鍛鐎电钖�
	 * 
	 * @param clazz
	 * @param id
	 */
	public Object loadById(Class<?> clazz, Serializable id);

	/**
	 * 閸旂姾娴囧陇鍐婚弶鈥叉閻ㄥ嫭瀵旀稊鍛鐎电钖�
	 * 
	 * @param hql
	 */
	public Object loadObject( String hql);

	/**
	 * 閸掔娀娅庨崚璺虹暰ID閻ㄥ嫭瀵旀稊鍛鐎电钖�
	 * 
	 * @param clazz
	 * @param id
	 */
	public void  delById(Class<?> clazz, Serializable id);

	/**
	 * 娣囨繂鐡ㄩ幋鏍ㄦ纯閺傛澘鍩楃�规碍瀵旀稊鍛鐎电钖�
	 * 
	 * @param obj
	 */
	public void saveOrUpdate(Object obj);

	/**
	 * 鐟佸懓娴囬崚璺虹暰缁崵娈戦幍锟芥箒閹镐椒绠欓崠鏍ь嚠鐠烇拷
	 * 
	 * @param clazz
	 */
	public List listAll(String clazz);

	/**
	 * 閸掑棝銆夌憗鍛版祰閸掕泛鐣剧猾鑽ゆ畱閹碉拷婀侀幐浣风畽閸栨牕顕挒锟�
	 * 
	 * @param clazz
	 * @param pageNo
	 * @param pageSize
	 */
	public List listAll(String clazz, int pageNo, int pageSize);

	/**
	 * 缂佺喕顓搁幐鍥х暰缁崵娈戦幍锟芥箒閹镐椒绠欓崠鏍ь嚠鐠烇拷
	 * 
	 * @param clazz
	 */
	public int countAll(String clazz);

	/**
	 * 閺屻儴顕楅幐鍥х暰缁崵娈戝陇鍐婚弶鈥叉閻ㄥ嫭瀵旀稊鍛鐎电钖�
	 * 
	 * @param hql
	 */
	public List query(String hql);

	/**
	 * 閸掑棝銆夐弻銉嚄閹稿洤鐣剧猾鑽ゆ畱濠娐ゅ喕閺夆�叉閻ㄥ嫭瀵旀稊鍛鐎电钖�
	 * 
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 */
	public List query(String hql, int pageNo, int pageSize);

	/**
	 * 缂佺喕顓搁幐鍥х暰缁崵娈戦弻銉嚄缂佹挻鐏�
	 * 
	 * @param hql
	 */
	public int countQuery(String hql);

	/**
	 * 閺夆�叉閺囧瓨鏌婇弫鐗堝祦鎼达拷
	 * 
	 * @param hql
	 */
	public int update(String hql);

	/**
	 * 娴犲氦绻涢幒銉︾潨閼惧嘲褰嘕DBC鏉╃偞甯�
	 */
	public Connection getConnection();
        
       
        
}