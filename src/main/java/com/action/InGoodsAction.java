package com.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.orm.Goods;
import com.orm.InGoods;
import com.orm.OrgStaff;
import com.orm.Organize;
import com.orm.SystemUser;
import com.service.GoodsService;
import com.service.InGoodsService;
import com.service.OrganizeService;
import com.service.UserService;

@SuppressWarnings("serial")
public class InGoodsAction  extends ActionSupport{
	private InGoods inGoods;
	private Goods goods;
	private File suppliercard;
	private File goodscard;
	@Autowired
    OrganizeService orgservice;
    @Autowired
    UserService userService;
    @Autowired
    InGoodsService inGoodsService;
    @Autowired
    GoodsService goodsService;
    public String addInGoods() throws Exception {
    	if(suppliercard!=null){
    		byte[] buffer = null;
    		FileInputStream fis = new FileInputStream(suppliercard);
    		ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
            byte[] b = new byte[1000];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();
            if(buffer!=null)
            	inGoods.setSuppliercard(buffer);
    	}
    	
    	HttpServletRequest request = ServletActionContext.getRequest(); 
		SystemUser user=(SystemUser)request.getSession().getAttribute("loginuser");
		inGoods.setOrgcode(user.getOrgcode());
		inGoods.setOrgname(user.getOrgname());
		System.out.println(inGoods.getIntime());
		if(inGoods.getIntime()==null)
			inGoods.setIntime(new Date());
		System.out.println(inGoods.getIntime());
    	boolean flag=inGoodsService.saveOrUpdate(inGoods);
        if (flag) {
        	request.setAttribute("orgcode",user.getOrgcode());
            return "addingoodsSuccess";
        }
        return ERROR;
    }
    public String addGoods() throws Exception {
    	//System.out.println("123123"+goodscard.getAbsolutePath());
    	if(goodscard!=null){
    		byte[] buffer = null;
    		FileInputStream fis = new FileInputStream(goodscard);
    		ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
            byte[] b = new byte[1000];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();
            if(buffer!=null)
            	goods.setGoodscard(buffer);
    	}
    	boolean flag=goodsService.saveOrUpdate(goods);
        if (flag) {
        	//System.out.println("get");
            return "addgoodsSuccess";
        }
        return ERROR;
    }
    public String seeInGoods() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest(); 
		inGoods=inGoodsService.findInGoodsByid(Integer.valueOf(request.getParameter("id")));
        if(inGoods!=null){
        	request.setAttribute("id", inGoods.getId());
        	return "goodslist";
        }
		return ERROR;
    }
    public String seeManageInGoods() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest(); 
		inGoods=inGoodsService.findInGoodsByid(Integer.valueOf(request.getParameter("id")));
        if(inGoods!=null){
        	request.setAttribute("id", inGoods.getId());
        	return "goodslistforsearch";
        }
		return ERROR;
    }
    public String modIngoods() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest(); 
    	InGoods inGoods=inGoodsService.findInGoodsByid(Integer.valueOf(request.getParameter("id")));
        if (inGoods!=null) {
        	request.getSession().setAttribute("supplierimage", inGoods.getSuppliercard());
        	setInGoods(inGoods);
            return "getMod";
        }
		return ERROR;
    }
    public String savemod() throws Exception {
        //娣诲姞鐢ㄦ埛
		HttpServletRequest request = ServletActionContext.getRequest(); 
		InGoods inGoods2=inGoodsService.findInGoodsByid(inGoods.getId());
		if(suppliercard==null){
			inGoods.setSuppliercard(inGoods2.getSuppliercard());
		}else{
			byte[] buffer = null;
			FileInputStream fis = new FileInputStream(suppliercard);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
	        byte[] b = new byte[1000];  
	        int n;  
	        while ((n = fis.read(b)) != -1) {  
	            bos.write(b, 0, n);  
	        }  
	        fis.close();  
	        bos.close();  
	        buffer = bos.toByteArray();
	        if(buffer!=null)
	        	inGoods.setSuppliercard(buffer);
			SystemUser user=(SystemUser)request.getSession().getAttribute("loginuser");
			inGoods.setOrgcode(user.getOrgcode());
			inGoods.setOrgname(user.getOrgname());
			System.out.println(inGoods.getIntime());
			if(inGoods.getIntime()==null)
				inGoods.setIntime(new Date());
			System.out.println(inGoods.getIntime());
	    	boolean flag=inGoodsService.saveOrUpdate(inGoods);
	        if (flag) {
	            return "addingoodsSuccess";
	        }
	        return ERROR;
		}
		return "addingoodsSuccess";
    }
    public String delInGoods() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest(); 
		boolean flag=inGoodsService.deleteInGoodsByid(request.getParameter("id"));
		System.out.println("sxt");
        if (flag) {
        	System.out.println("sxt22");
            return "ingoodslist";
        }
        return ERROR;
    }
    public String delGoods() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest(); 
		boolean flag=goodsService.deleteGoodsByid(request.getParameter("id"));
        if (flag) {
            return "goodslist";
        }
        return ERROR;
    }
    public String seeProve() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest(); 
		//System.out.println(request.getParameter("id"));
		InGoods inGoods=inGoodsService.findInGoodsByid(Integer.valueOf(request.getParameter("id")));
		
        if (inGoods!=null) {
        	if(inGoods.getSuppliercard()!=null){
        		request.setAttribute("supplierimage", inGoods.getSuppliercard());
        	}else{
        		String image="您没有上传资质证明！";
        		request.setAttribute("supplierimage", image.getBytes());
        	}
            return "seecard";
        }
        return ERROR;
    }
    public String seeCard() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest();
		Goods goods=goodsService.findGoodsByid(request.getParameter("id"));
		
        if (goods!=null) {
        	if(goods.getGoodscard()!=null){
        		request.setAttribute("cardimage", goods.getGoodscard());
        	}else{
        		String image="您没有上传合格证！";
        		request.setAttribute("cardimage", image.getBytes());
        	}
            return "seegoodscard";
        }
        return ERROR;
    }
    public String listInGoods() throws Exception {
    	//rubbishService.listAllOutRubbish();
    	HttpServletRequest request = ServletActionContext.getRequest(); 
		String orgcode=((SystemUser)request.getSession().getAttribute("loginuser")).getOrgcode();
		request.setAttribute("orgcode",orgcode);
        return "ingoodslist";
    }
    public String ListAll() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest(); 
    	request.getSession().removeAttribute("orglist");
		request.getSession().setAttribute("orglist",orgservice.listAllOrganize());
        return "ListAll";
    }
    public String ListMine() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest(); 
		String orgcode=((SystemUser)request.getSession().getAttribute("loginuser")).getOrgcode();
		List<Organize> myshopslist=orgservice.findOrganizeByBelongcode(orgcode);
		if(myshopslist!=null){
			request.getSession().removeAttribute("myshopslist");
			request.getSession().setAttribute("myshopslist",myshopslist);
			return "ListMine";
		}
        return ERROR;
    }

    
	public InGoods getInGoods() {
		return inGoods;
	}
	public void setInGoods(InGoods inGoods) {
		this.inGoods = inGoods;
	}
	public File getSuppliercard() {
		return suppliercard;
	}
	public void setSuppliercard(File suppliercard) {
		this.suppliercard = suppliercard;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public File getGoodscard() {
		return goodscard;
	}
	public void setGoodscard(File goodscard) {
		this.goodscard = goodscard;
	}
    
    
}
