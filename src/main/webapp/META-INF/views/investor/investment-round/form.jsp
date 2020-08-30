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

<acme:form readonly = "true">
	<acme:form-textbox code="investor.investment-round.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="investor.investment-round.form.label.creation" path="creation"/>
	<acme:form-textbox code="investor.investment-round.form.label.round" path="round"/>
	<acme:form-textbox code="investor.investment-round.form.label.title" path="title"/>
	<acme:form-textbox code="investor.investment-round.form.label.description" path="description"/>
	<acme:form-textbox code="investor.investment-round.form.label.amount" path="amount"/>
	<acme:form-textbox code="investor.investment-round.form.label.optional" path="optional"/>


  	<button type="button" onclick="javascript: clearReturnUrl(); redirect('/investor/application/create?id=${id}')"
            class="btn btn-default">
            <acme:message code="investor.investment-round.form.button.associate"/>
    </button>
	<acme:form-return code="investor.investment-round.form.button.return"/>
	
</acme:form>
