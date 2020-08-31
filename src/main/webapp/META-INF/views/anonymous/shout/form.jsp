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
	<acme:form-textbox code="anonymous.shout.form.label.author" path="author"/>
	<jstl:if test="${command !='create'}">
	<acme:form-moment code="anonymous.shout.form.label.moment" path="moment"/>
	</jstl:if>
	<acme:form-textbox code="anonymous.shout.form.label.text" path="text"/>
	
	<jstl:if test="${command !='show'}">
	<acme:form-submit code="anonymous.shout.form.button.create" action="/anonymous/shout/create"/>
	</jstl:if>
	<acme:form-return code="anonymous.shout.form.button.return"/>
</acme:form>
