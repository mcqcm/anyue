<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <s:property value="fieldErrors"/>
    <center>
       
        <h1 style="font-size: 26px; margin-top:10%;" >对不起!<br/><br/><br/><br/>操作失败!不允许上传此类型文件!</h1>
    </center>
    </body>
</html>
