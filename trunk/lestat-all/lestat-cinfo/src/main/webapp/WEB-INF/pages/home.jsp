<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <title>LeStat: CInfo</title>
  <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/handsontable/jquery.handsontable.full.min.css">
  <link href="${pageContext.request.contextPath}/handsontable/jquery.handsontable.bootstrap.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/compatible/bootstrap-handsontable.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/d3.css" rel="stylesheet">
  <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/current-row-col.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/toastr/toastr.css" rel="stylesheet" type="text/css" />
  
  <meta name="_csrf" content="${_csrf.token}"/>
  <!-- default header name is X-CSRF-TOKEN -->
  <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>

 <!-- 
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>
 -->

	<div id="test-div">
	</div>

<sec:authorize access="hasRole('ROLE_USER')"> 
<div style="display:none">
        <c:url value="/j_spring_security_logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>
</div>
</sec:authorize>
		
<nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
   <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">LeStat: CInfo</a>
    </div>
  
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right">
      <sec:authorize access="hasRole('ROLE_USER')"> 
      
        <li><a href="#">
        <c:if test="${pageContext.request.userPrincipal.name != null}">
			
				${pageContext.request.userPrincipal.name}
        </c:if>
		</a>
		</li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Action <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="javascript:formSubmit()">Logout</a></li>
            <!-- 
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li class="divider"></li>
            <li><a href="#">Separated link</a></li>
             -->
          </ul>
        </li>
      
      </sec:authorize>
      <sec:authorize access="!hasRole('ROLE_USER')"> 
        <li><a href="#" id="a2login">
        login
		</a>
		</li>
     </sec:authorize>
      
      </ul>
    </div><!-- /.navbar-collapse -->
    
  </div><!-- /.container-fluid -->
</nav> 

		

	

   <div style="display:none">
   <hr />
   <p class="active-tab"><strong>激活的标签页</strong>：<span></span></p>
   <p class="previous-tab"><strong>前一个激活的标签页</strong>：<span></span></p>
   <hr>
   </div>

	<ul class="nav nav-tabs">
	 <li class="active dropdown">
      <a href="${viewMenu.url}" id="${viewMenu.id}" class="dropdown-toggle" 
         data-toggle="dropdown">${viewMenu.name}<b class="caret"></b></a>
      <ul class="dropdown-menu" role="menu" aria-labelledby="${viewMenu.id}" id="viewTopMenu">
      	<c:forEach var="item" items="${viewMenu.subMenuList}">
    	  	 <li><a href="${item.url}" tabindex="-1" data-toggle="tab" data-url="${item.dataUrl}">${item.name}</a></li>
		</c:forEach>
      </ul>
   </li>
   
   <sec:authorize access="hasRole('ROLE_ADMIN')"> 
   <li class="dropdown">
      <a href="${adminMenu.url}" id="${adminMenu.id}" class="dropdown-toggle" 
         data-toggle="dropdown">${adminMenu.name}<b class="caret"></b></a>
      <ul class="dropdown-menu" role="menu" aria-labelledby="${adminMenu.id}" id="adminTopMenu">
      	<c:forEach var="item" items="${adminMenu.subMenuList}">
    	  	 <li><a href="${item.url}" tabindex="-1" data-toggle="tab" data-url="${item.dataUrl}">${item.name}</a></li>
		</c:forEach>
      </ul>
   </li>
   </sec:authorize>
   
     <li class="dropdown">
      <a href="${uiMenu.url}" id="${uiMenu.id}" class="dropdown-toggle" 
         data-toggle="dropdown">${uiMenu.name}<b class="caret"></b></a>
      <ul class="dropdown-menu" role="menu" aria-labelledby="${uiMenu.id}" id="linkTopMenu">
      	<c:forEach var="item" items="${uiMenu.subMenuList}">
    	  	 <li><a href="${item.url}" tabindex="-1" data-toggle="tab" data-url="${item.dataUrl}">${item.name}</a></li>
		</c:forEach>
      </ul>
   </li>
   
</ul>
<div id="myTabContent" class="tab-content">
   <div class="tab-pane fade in active" id="viewTitle">
      <!-- <p id="pMyView"></p> -->
   </div>
  <div class="tab-pane fade in active" id="viewContent">
      <!-- <p id="pMyView"></p> -->
   </div>
</div>

   <!-- -->
   <script src="${pageContext.request.contextPath}/jquery/jquery.min.js"></script>
   <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
   <script src="${pageContext.request.contextPath}/d3/d3.min.js"></script>
   <script src="${pageContext.request.contextPath}/handsontable/jquery.handsontable.full.min.js"></script>
   <script src="${pageContext.request.contextPath}/js/home.js"></script>
   <script src="${pageContext.request.contextPath}/toastr/toastr.js"></script>
   <script src="${pageContext.request.contextPath}/js/myToastr.js"></script>
</body>
</html>