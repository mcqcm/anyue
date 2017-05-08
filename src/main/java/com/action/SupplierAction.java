package com.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Supplier;
import com.service.SupplierService;

@SuppressWarnings("serial")
public class SupplierAction extends ActionSupport {

	private Supplier supplier;
	@Autowired
	SupplierService supplierService;
	@Override
    public String execute() throws Exception {
        return ERROR;
    }
	public String ListAll() throws Exception {
        return "List";
    }
	public String addsupplier() throws Exception {
        //添加用户
        boolean flag=supplierService.saveOrUpdate(getSupplier());
        if (flag) {
            return "addSuccess";
        }
        return ERROR;
    }
	public String modsupplier() throws Exception {
        //添加用户
		HttpServletRequest request = ServletActionContext.getRequest(); 
		Supplier supplyer=supplierService.findSupplierByid(request.getParameter("id"));
        if (supplyer!=null) {
        	setSupplier(supplyer);
            return "modpage";
        }
        return ERROR;
    }
	public String delsupplier() throws Exception {
        //添加用户
		HttpServletRequest request = ServletActionContext.getRequest(); 
		boolean flag=supplierService.deleteSupplierByid(request.getParameter("id"));
        if (flag) {
            return "List";
        }
        return ERROR;
    }
    public String exit() {
        ActionContext.getContext().getSession().clear();
        return "exit";
    }
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

}
