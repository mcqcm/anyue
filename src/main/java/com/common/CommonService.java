package com.common;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
@Service
public class CommonService {
	@Autowired
	BaseDao dao;

	public String listEntity(CommonListEntity listEntity) throws ParseException {
		String entity=listEntity.getEntity();
		String draw=listEntity.getDraw();
		String start=listEntity.getStart();
		String length=listEntity.getLength();
	    ArrayList<String> columnsearchvalue=listEntity.getColumnsearchvalue();
	    ArrayList<Integer> searchcol=listEntity.getSearchcol();
	    //�ܼ�¼��
	    int recordsTotal=0;

	    //���˺��¼��
	    int recordsFiltered=0;
	    
	    //��������
	    String[] cols=listEntity.getCols().split(",");

	    String orderColumn = cols[Integer.parseInt(listEntity.getOrdercolumn())];
	    if(orderColumn.equals("number")){
	    	orderColumn = cols[Integer.parseInt(listEntity.getOrdercolumn())+1];
	    }
	    String orderDir=listEntity.getOrderDir();
	    String searchValue=listEntity.getSearchvalue();
	    String searchColumn=listEntity.getSearchcolumn();
	    List<String> sArray = new ArrayList<String>();
	    if (searchValue!=null&&!(searchValue.equals(""))) {//����������ģ����ѯ
	    		if((searchColumn!=null)&&!(searchColumn.equals(""))&&!(searchColumn.equals("ALL"))){
	    			String[] searchColumns = searchColumn.split(",");
	    			for(int i=0;i<searchColumns.length;i++){
	    				sArray.add("t."+searchColumns[i]+" like '%" + searchValue + "%'");
	    			}
	    		}else{
	    			int i=0;
	    			if(cols[0].equals("number")){
	    				i=1;
	    				orderColumn = cols[Integer.parseInt(listEntity.getOrdercolumn()+1)];
	    			}
	    			for(;i<cols.length;i++){
	    				sArray.add("t."+cols[i]+" like '%" + searchValue + "%'");
	    			}
	    		}
	    }

	    String individualSearch = "";
	    if (sArray.size() == 1) {
	        individualSearch = sArray.get(0);
	    } else if (sArray.size() > 1) {
	        for (int i = 0; i < sArray.size() - 1; i++) {
	            individualSearch += sArray.get(i) + " or ";
	        }
	        individualSearch += sArray.get(sArray.size() - 1);//������ģ����ѯ����
	    }
	    String columnsearchsql="";
	    if(columnsearchvalue.size()>0){
	    	int lengths=columnsearchvalue.size();
	    	for(int i=0;i<lengths-1;i++){
	    		String[] datesearchs=columnsearchvalue.get(i).split(" to ");
	    		if(datesearchs.length==2){
	    			String date1=datesearchs[0].substring(0, 10);
	    			String date2=datesearchs[1].substring(0, 10);
	    			columnsearchsql+="t."+cols[searchcol.get(i)]+" between to_date('"+date1+"','YYYY-MM-DD') and to_date('"+date2+"','YYYY-MM-DD') and ";
	    		}else{
	    			columnsearchsql+="t."+cols[searchcol.get(i)]+" like '%"+columnsearchvalue.get(i)+"%' and ";
	    		}
	    	}
    		String[] datesearchs=columnsearchvalue.get(lengths-1).split(" to ");
    		if(datesearchs.length==2){
    			String date1=datesearchs[0].substring(0, 10);
    			String date2=datesearchs[1].substring(0, 10);
    			columnsearchsql+="t."+cols[searchcol.get(lengths-1)]+" between to_date('"+date1+"','YYYY-MM-DD') and to_date('"+date2+"','YYYY-MM-DD') ";
    		}else{
    			columnsearchsql+="t."+cols[searchcol.get(lengths-1)]+" like '%"+columnsearchvalue.get(lengths-1)+"%' ";
    		}
	    }//ÿ����Ӧ��ģ����ѯ����
	    List Entitys = new ArrayList();
	    String condition=listEntity.getCondition();
	    
	    if(condition==null||condition.equals("")){
	    		recordsTotal=dao.countAll(entity);
	    }else{
	    	recordsTotal=(dao.query("FROM "+entity+" as t WHERE "+condition)).size();
	    	if(individualSearch.equals("")){
	    		individualSearch =condition;
	    	}else{
	    		individualSearch ="(" + individualSearch + ")"+" and "+condition;//��������������ģ��
	    	}
	    }
    	if(!(columnsearchsql.equals(""))){
	    	if(individualSearch.equals("")){
	    		individualSearch =columnsearchsql;//û��������Ҳû��ȫ��ģ����ֻ�ж�Ӧ��ģ��
	    	}else if(condition==null||condition.equals("")){
	    		individualSearch ="(" + individualSearch + ")"+" and "+columnsearchsql;//������Ϊ�� individualsearch��or���ӵġ���ʱ��ȫ��ģ��
	    	}else{
	    		individualSearch+=" and "+columnsearchsql;//������Ϊ�գ���ʱȫ��ģ�����л���
	    	}
    	}
	    
        String searchSQL = "";
        String sql = "FROM " + entity +" as t";
        if (individualSearch != "") {
            searchSQL = " where " + individualSearch;
        }
        sql += searchSQL+" order by t." + orderColumn + " " + orderDir;
        int startrow=Integer.parseInt(start);
        int pagesize=Integer.parseInt(length);
        Entitys=dao.query(sql,startrow/pagesize+1,pagesize);
        recordsFiltered=dao.query(sql).size();
        Map<Object, Object> info = new HashMap<Object, Object>();
        info.put("data", Entitys);
        info.put("recordsTotal", recordsTotal);
        info.put("recordsFiltered", recordsFiltered);
        info.put("draw", draw);
//        JsonConfig jsonConfig=new JsonConfig();
//        jsonConfig.setIgnoreDefaultExcludes(false);
//        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
//        jsonConfig.setExcludes(new String[] {entity.toLowerCase(),entity.toLowerCase()+"s"});
        //String json = JSONSerializer.toJSON(info, jsonConfig).toString();
        String json="";
		try {
			json = DateJsonValueProcessor.ObjectToJson(info, new String[] {entity.toLowerCase(),entity.toLowerCase()+"s"}, "yyyy-MM-dd");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
		// TODO Auto-generated method stub
		
	}
}
