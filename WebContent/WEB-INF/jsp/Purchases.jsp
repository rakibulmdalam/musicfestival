<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="purchase" scope="request" class="de.tum.in.dbpra.model.bean.PurchaseBean"></jsp:useBean>
<html>
<jsp:include page="head.jsp" >
  <jsp:param name="pageTitle" value="Purchases" />
</jsp:include>
<body>
	<jsp:include page="visitorHeader.jsp">
		<jsp:param name="firstName" value="${user.getUserName()}" />
		<jsp:param name="photoUrl" value="${user.getPhotoUrl()}" />
		<jsp:param name="currentTab" value="purchases" />
	</jsp:include>
<section class="section">
		<div class="columns">
			<div class="column is-6 is-offset-3">
	
				<% if (request.getAttribute("error") != null) { %>
						<h2 class="festival-day-label"><%= request.getAttribute("error") %></h2>
					
						<% } else { %>
					
				<table class="table">
				  <thead>
				    <tr>
				    <th>Item No. </th>
				      <th>Product</th>
				      <th>Price</th>
				      <th>Purchase Date</th>
				      <th>Quantity</th>
				      <th>Shop purchase in</th>
				      </tr>
				  </thead>
				  <tbody>
				  <% for (int i = 0 ; i < purchase.getPurchaseBeans().size() ; i++) { %>
				    <tr>
				      <th><%= i+1 %></th>
					  
						<td><%= purchase.getPurchaseBeans().get(i).getProduct() %></td> 
						<td><%= purchase.getPurchaseBeans().get(i).getPrice() %></td> 
						 <td><%= purchase.getPurchaseBeans().get(i).getPurchaseDate() %></td> 
						 <td><%= purchase.getPurchaseBeans().get(i).getQuantity() %></td>
						<td><%= purchase.getPurchaseBeans().get(i).getShop() %></td> 	
				
							<% } %>
						<% } %>
					 </tr>
				  </tbody>
				</table>
			</div>
		</div>
	</section>
</body>
</html>