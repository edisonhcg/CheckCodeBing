<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <!-- 该函数无法执行的话，可能是没有通知浏览器不要缓存的原因 -->
  <script type="text/javascript">
  	function toreal(){
  		document.location.href="Login.jsp";
  	}
  	
  </script>
  <body>
    <form action="/check/demo" method="post">     <br>
  		用  户 名：<input type="text" name="username">     <br>
  		密      码：<input type="password" name="password">     <br>
  						<input type="text" name="code">
  						<img alt="验证码" src="/CheckCodeBing/checkbing/Image"><!-- 内部自动发送请求，加载验证码 -->
    					<a href="javascript:toreal()">看不清</a><br/>
  						<input type="submit"  value="提交">
  </form>
  
  </body>
</html>
