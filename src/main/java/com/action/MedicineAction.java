package com.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Medinfo;
import com.orm.Supplier;
import com.orm.SystemUser;
import com.service.MedicineService;
import com.service.SupplierService;
import com.service.UserService;

@SuppressWarnings("serial")
public class MedicineAction extends ActionSupport {

   private Medinfo medicineinfo;
   private String supid;
   @Autowired
   MedicineService medicineservice;
   @Autowired
   SupplierService supplierservice;
	@Override
    public String execute() throws Exception {
        return ERROR;
    }
	public String ListAll() throws Exception {
        return "ListAll";
    }
	public String List() throws Exception {
        return "List";
    }
	public String addmedicine() throws Exception {
        //添加用户
//		Date makedate=medicineinfo.getMaketime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		medicineinfo.setMaketime(format.parse(format.format(medicineinfo.getMaketime())));
		medicineinfo.setUserfultime((format.parse(format.format(medicineinfo.getUserfultime()))));
		medicineinfo.setSupplier(supplierservice.findSupplierByid(getSupid()));
		if(medicineinfo.getMaketime()==null)
			medicineinfo.setMaketime(new Date());
		if(medicineinfo.getUserfultime()==null)
			medicineinfo.setUserfultime(new Date());
        boolean flag=medicineservice.saveOrUpdate(medicineinfo);
        if (flag) {
            return "addSuccess";
        }
        return ERROR;
    }
	public String modmedicine() throws Exception {
        //添加用户
		HttpServletRequest request = ServletActionContext.getRequest(); 
		Medinfo Medinfo=medicineservice.findMedicineByid(request.getParameter("id"));
        if (Medinfo!=null) {
        	setMedicineinfo(Medinfo);
            return "modpage";
        }
        return ERROR;
    }
	public String delmedicine() throws Exception {
        //添加用户
		HttpServletRequest request = ServletActionContext.getRequest(); 
		boolean flag=medicineservice.deleteMedicineByid(request.getParameter("id"));
        if (flag) {
            return "ListAll";
        }
        return ERROR;
    }
    public String exit() {
        ActionContext.getContext().getSession().clear();
        return "exit";
    }
	public Medinfo getMedicineinfo() {
		return medicineinfo;
	}
	public void setMedicineinfo(Medinfo medicineinfo) {
		this.medicineinfo = medicineinfo;
	}
	public String getSupid() {
		return supid;
	}
	public void setSupid(String supid) {
		this.supid = supid;
	}

}
