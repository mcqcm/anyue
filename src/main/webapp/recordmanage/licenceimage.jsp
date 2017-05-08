<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" import="java.util.*,java.io.*" %> 
			<%
			//System.out.println("hello::::"+request.getParameter("image"));
			if(request.getSession().getAttribute("imagelicence")!=null){
			InputStream in = new ByteArrayInputStream((byte[])request.getSession().getAttribute("imagelicence"));
			int len;
            byte[] buf = new byte[1024];
            response.setContentType("image/jpeg");
            OutputStream toClient = response.getOutputStream();
            out.clear();
            out = pageContext.pushBody();
            while ((len = in.read(buf, 0, 1024)) != -1) {
            	toClient.write(buf, 0, len);
            }
            toClient.close();
			}
			%>
