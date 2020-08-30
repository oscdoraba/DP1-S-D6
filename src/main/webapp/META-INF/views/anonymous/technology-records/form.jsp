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
	<acme:form-textbox code="anonymous.technologyRecords.form.label.title" path="title"/>
	<acme:form-textbox code="anonymous.technologyRecords.form.label.activitySector" path="activitySector"/>
	<acme:form-textbox code="anonymous.technologyRecords.form.label.inventor" path="inventor"/>
	<acme:form-textarea code="anonymous.technologyRecords.form.label.description" path="description"/>
	<acme:form-textbox code="anonymous.technologyRecords.form.label.website" path="website"/>
	<acme:form-textbox code="anonymous.technologyRecords.form.label.email" path="email"/>
	<acme:form-textbox code="anonymous.technologyRecords.form.label.indication" path="indication"/>
	<acme:form-integer code="anonymous.technologyRecords.form.label.stars" path="stars"/>
	

    
	<acme:form-return code="anonymous.technologyRecords.form.button.return"/>
</acme:form>
