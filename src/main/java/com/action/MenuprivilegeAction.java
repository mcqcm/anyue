package com.action;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.Menuchecked;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.SystemMenu;
import com.orm.SystemRole;
import com.service.MenuService;
import com.service.RoleService;

@SuppressWarnings("serial")
public class MenuprivilegeAction extends ActionSupport {

	private List<SystemRole> roleslist;
	private String result;
	private JSONArray jsonarray;
	private Set<SystemMenu> rolemenus;
	public JSONArray getJsonarray() {
		return jsonarray;
	}
	public void setJsonarray(JSONArray jsonarray) {
		this.jsonarray = jsonarray;
	}


	@Autowired
    RoleService roleService;
	@Autowired
	MenuService menuService;
	public List<SystemRole> getRoleslist() {
		return roleslist;
	}
	public void setRoleslist(List<SystemRole> roleslist) {
		this.roleslist = roleslist;
	}
	@Override
    public String execute() throws Exception {
		roleslist = roleService.listAllRole();
		HttpServletRequest request = ServletActionContext.getRequest();
		String roleid=request.getParameter("roleid");
		if(roleid==null){
			roleid="";
		}else{
			request.setAttribute("roleid",roleid);
			SystemRole role=roleService.findRoleByid(roleid);
			request.setAttribute("rolename",role.getRolename());
		}
		setJsonarray(getTreeMenu(roleid));
        if (roleslist != null&&jsonarray!=null) {
        	request.setAttribute("jsonarray", jsonarray);
            return "menulist";
        }
        return ERROR;
    }
	public String ListAll() throws Exception {
        return "ListAll";
    }
	public String List() throws Exception {
        return "List";
    }
	public String saveprivilege() throws Exception {
		String[] items=result.split(";");
		String roleid=items[items.length-1];
		SystemRole role=roleService.findRoleByid(roleid);
		Set<SystemMenu> menuset=new HashSet<SystemMenu>(0);
		for(int i=0;i<items.length-1;i++){
			SystemMenu menu=menuService.findMenuByid(items[i]);
			menuset.add(menu);
		}
		role.setSystemmenus(menuset);
		boolean flag=roleService.saveOrUpdate(role);
		if(flag){
			return "savesuccess";
		}
		return ERROR;
    }

	
	public JSONArray getTreeMenu(String roid)
	{
		try
		{
			if(roid.equals("")){
				roid="0";
			}
			SystemRole role=roleService.findRoleByid(roid);
			if(role!=null)
				rolemenus=role.getSystemmenus();
			List<SystemMenu> allmenus=menuService.listAllMenu();
			JSONArray jsonarray=new JSONArray();
			Integer temp;
			int pre_level=1;
			
					
					if(allmenus!=null && allmenus.size()>0)
					 {
					 
						
					    JSONObject jsonObject;
					 
					 	for (int i=0;i<allmenus.size();i++)
						{
					 		Menuchecked org=new Menuchecked();
					 		SystemMenu menu=allmenus.get(i);
							temp=menu.getId();
							//org.setChecked(true);
							if(rolemenus!=null&&rolemenus.size()>0)
							{
								Iterator<SystemMenu> it=rolemenus.iterator();
								while(it.hasNext()){
									if(temp==it.next().getId()){
										org.setChecked(true);
									}
								}
							}
							org.setOpen(true);
							org.setId(String.valueOf(temp));
							org.setName(menu.getMenuname());
							org.setPId(String.valueOf(menu.getParentid()));

							//jsonObject = JSONObject.fromObject(org);
							jsonObject = JSON.parseObject(JSON.toJSONString(org));;
							jsonarray.add(i,jsonObject); 	
					    }
					  }
					return jsonarray;
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
