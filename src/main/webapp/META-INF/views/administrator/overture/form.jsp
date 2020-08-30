<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.overture.form.label.title" path="title"/>
	<jstl:if test="${command !='create'}">
	<acme:form-textbox code="administrator.overture.form.label.creation" path="creation" readonly="true"/>
	</jstl:if>
	<acme:form-textbox code="administrator.overture.form.label.deadline" path="deadline"/>
	<acme:form-textbox code="administrator.overture.form.label.description" path="description"/>
	<acme:form-textbox code="administrator.overture.form.label.minMoney" path="minMoney"/>
	<acme:form-textbox code="administrator.overture.form.label.maxMoney" path="maxMoney"/>
	<acme:form-textbox code="administrator.overture.form.label.email" path="email"/>
	
	<acme:form-submit test="${command == 'show' }"
	 code="administrator.overture.form.button.update" 
	 action="/administrator/overture/update"/>
	
	<acme:form-submit test="${command == 'show' }"
	 code="administrator.overture.form.button.delete" 
	 action="/administrator/overture/delete"/>
	 
	<acme:form-submit test="${command == 'update' }"
 	 code="administrator.overture.form.button.update"
	 action="/administrator/overture/update"/>
	 
	 <acme:form-submit test="${command == 'delete' }"
	 code="administrator.overture.form.button.delete" 
	 action="/administrator/overture/delete"/>
	
	
	<jstl:if test="${command =='create'}">
	<acme:form-submit code="administrator.overture.form.button.create" action="/administrator/overture/create"/>
	</jstl:if>
	<acme:form-return code="administrator.overture.form.button.return"/>
</acme:form>
