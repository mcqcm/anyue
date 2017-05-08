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
        //閸掓鍤幍锟介張澶屾暏閹达拷
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
		// 节点列表（散列表，用于临时存储节点对象）
		HashMap<Integer, MenuNode> nodeList = new HashMap<Integer, MenuNode>();
		// 根节点
		MenuNode root = new MenuNode();
		root.setId(0);
		root.setMenuname("菜单");
		nodeList.put(root.getId().intValue(), root);
		// 根据结果集构造节点列表（存入散列表）
		while(dataList.hasNext()){
			MenuNode node = new MenuNode();
			SystemMenu dataRecord=dataList.next();
			node.setId(dataRecord.getId());
			BeanUtils.copyProperties(dataRecord, node);
			node.setSystemroles(null);
			nodeList.put(node.getId().intValue(), node);
		}
		// 构造无序的多叉树
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
