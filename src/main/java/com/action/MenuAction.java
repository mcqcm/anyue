package com.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.common.MenuNode;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.SystemMenu;
import com.orm.SystemRole;
import com.orm.SystemUser;
import com.service.MenuService;

@SuppressWarnings("serial")
public class MenuAction extends ActionSupport {

	private SystemMenu menu;
	private static SystemMenu oldmenu;
	@Autowired
	MenuService menuService ;
	@Override
    public String execute() throws Exception {
        //鍒楀嚭鎵�鏈夌敤鎴�
        return ERROR;
    }
	public String ListAll() throws Exception {
        return "ListAll";
    }
	public String List() throws Exception {
        return "List";
    }
	public String addmenu(){
		if(oldmenu!=null){
			menu.setSystemroles(oldmenu.getSystemroles());
		}
		boolean flag=menuService.saveOrUpdate(menu);
		if(flag){
			return "addSuccess";
		}
		return ERROR;
	}
	public String delmenu(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		boolean flag=menuService.deleteMenuByid(request.getParameter("id"));
		if(flag){
			return "Success";
		}
		return ERROR;
	}
	public String modmenu(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		menu=menuService.findMenuByid(request.getParameter("id"));
		oldmenu=menu;
		if(menu!=null){
			return "modpage";
		}
		return ERROR;
	}
	public  void  query(){
		String jsonstr = "";
		try {
			jsonstr = getMenuJsonstr();
			reponseWriter(jsonstr);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getMenuJsonstr() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest(); 
		SystemRole role=((SystemUser)request.getSession().getAttribute("loginuser")).getSystemrole();
		Set<SystemMenu> menus=role.getSystemmenus();
		Iterator<SystemMenu> dataList = menus.iterator();  
		// �ڵ��б�ɢ�б�������ʱ�洢�ڵ����
		HashMap<Integer, MenuNode> nodeList = new HashMap<Integer, MenuNode>();
		// ���ڵ�
		MenuNode root = new MenuNode();
		root.setId(0);
		root.setMenuname("�˵�");
		nodeList.put(root.getId().intValue(), root);
		// ���ݽ��������ڵ��б�����ɢ�б�
		while(dataList.hasNext()){
			MenuNode node = new MenuNode();
			SystemMenu dataRecord=dataList.next();
			node.setId(dataRecord.getId());
			BeanUtils.copyProperties(dataRecord, node);
			node.setSystemroles(null);
			nodeList.put(node.getId().intValue(), node);
		}
		// ��������Ķ����
		Set entrySet = nodeList.entrySet();
		for (Iterator it = entrySet.iterator(); it.hasNext();) {
			MenuNode node = (MenuNode) ((Map.Entry) it.next()).getValue();
			node.setText(node.getMenuname());
			if (node.getParentid() == null || node.getParentid().equals("")) {
			} else {
				((MenuNode) nodeList.get(node.getParentid())).getChildren()
						.add(node);
			}
		}
		String json = JSON.toJSONString(root);
		return "[" + json + "]";
	}
	public  void reponseWriter(String jsonstr){
		HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().print(jsonstr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public SystemMenu getMenu() {
		return menu;
	}
	public void setMenu(SystemMenu menu) {
		this.menu = menu;
	}
}
