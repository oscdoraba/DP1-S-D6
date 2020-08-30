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
	<acme:form-textbox code="entrepreneur.application.form.label.ticker" path="ticker" readonly="true"/>
	<acme:form-moment code="entrepreneur.application.form.label.dateOfCreation" path="dateOfCreation" readonly="true"/>
	<acme:form-textarea code="entrepreneur.application.form.label.statement" path="statement" readonly="true"/>
	<acme:form-money code="entrepreneur.application.form.label.moneyOffer" path="moneyOffer" readonly="true"/>
	<acme:form-textbox code="entrepreneur.application.form.label.aceptacion" path="aceptacion" readonly="true"/>
	
	<acme:form-select code="entrepreneur.application.form.label.aceptacionFutura" path="aceptacion">
	<acme:form-option code="entrepreneur.application.form.label.true" value="true"/> 
 	<acme:form-option code="entrepreneur.application.form.label.false" value="false"/>
	</acme:form-select>

	<acme:form-textarea code="entrepreneur.application.form.label.justificacion" path="justificacion" />
	
	
	<acme:form-submit test="${command == 'show' }"
	 code="entrepreneur.application.form.button.update" 
	 action="/entrepreneur/application/update"/>
	 
	 <acme:form-submit test="${command == 'update' }"
 	 code="entrepreneur.application.form.button.update"
	 action="/entrepreneur/application/update"/>
	
	<acme:form-return code="entrepreneur.application.form.button.return"/>
</acme:form>
