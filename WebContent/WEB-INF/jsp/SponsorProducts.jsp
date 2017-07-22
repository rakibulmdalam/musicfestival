<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="de.tum.in.dbpra.model.bean.ProductBean" %>

<!DOCTYPE html>
	
<jsp:useBean id="user" scope="session"
	class="de.tum.in.dbpra.model.bean.UserAccountBean"></jsp:useBean>

<html>

<jsp:include page="head.jsp" >
  <jsp:param name="pageTitle" value="Products" />
</jsp:include>

<body>
	<jsp:include page="sponsorHeader.jsp">
		<jsp:param name="name" value="${user.getUserName()}" />
		<jsp:param name="photoUrl" value="${user.getPhotoUrl()}"/>
		<jsp:param name="hasProducts" value="true" />
		<jsp:param name="currentTab" value="products" />
	</jsp:include>
	
	<div class="section">
		<div class="columns">
			<div class="column is-offset-one-quarter is-half">
				<div class="content has-text-centered">
					<h2>Products</h2>
				</div>
				<div class="content">
					
					<h2>List of Products</h2>
					<table>
						<tr>
							<td>Type</td>
							<td>Price (euro)</td>
						</tr>
						<% 
						ArrayList<ProductBean> products = (ArrayList<ProductBean>) request.getAttribute("products");
						if(products.size() > 0){
							for( ProductBean product: products){ 
						%>
						<tr>
							<td>
								<%=product.getType() %>
							</td>
							<td>
								<%=product.getPrice() %>
							</td>
						</tr>
						<%
							}
						} %>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>