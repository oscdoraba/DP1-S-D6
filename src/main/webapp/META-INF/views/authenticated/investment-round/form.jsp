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
	<acme:form-textbox code="authenticated.investment-round.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="authenticated.investment-round.form.label.creation" path="creation"/>
	<acme:form-textbox code="authenticated.investment-round.form.label.round" path="round"/>
	<acme:form-textbox code="authenticated.investment-round.form.label.title" path="title"/>
	<acme:form-textbox code="authenticated.investment-round.form.label.description" path="description"/>
	<acme:form-textbox code="authenticated.investment-round.form.label.amount" path="amount"/>
	<acme:form-textbox code="authenticated.investment-round.form.label.optional" path="optional"/>


<%-- 	<acme:form-submit code="authenticated.investment-round.form.button.create" action="/authenticated/investment-round/create"/> --%>
	<acme:form-return code="authenticated.investment-round.form.button.return"/>
	
	<jstl:if test="${command == 'show' }">
	<button type="button" onclick="javascript: clearReturnUrl(); redirect('/authenticated/activity/list?id=${id}')"
            class="btn btn-default">
            <acme:message code="authenticated.investment-round.form.button.activities"/>
    </button>
    </jstl:if>	
	
</acme:form>
