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
	<acme:form-textbox code="entrepreneur.investment-round.form.label.ticker" path="ticker" placeholder="For example: INI-20-342789"/>
	<jstl:if test="${command !='create'}">
	<acme:form-textbox code="entrepreneur.investment-round.form.label.creation" path="creation" readonly="true"/>
	</jstl:if>
	<acme:form-textbox code="entrepreneur.investment-round.form.label.round" path="round" placeholder="SEED,ANGEL,SERIES_A,SERIES_B,SERIES_C,BRIDGE"/>
	<acme:form-textbox code="entrepreneur.investment-round.form.label.title" path="title"/>
	<acme:form-textbox code="entrepreneur.investment-round.form.label.description" path="description"/>
	<acme:form-textbox code="entrepreneur.investment-round.form.label.amount" path="amount"/>
	<acme:form-textbox code="entrepreneur.investment-round.form.label.optional" path="optional"/>
	<jstl:if test="${command !='create'}">
	<acme:form-textbox code="entrepreneur.investment-round.form.label.finalMode" path="finalMode" readonly="true"/>
	
	<acme:form-select code="entrepreneur.investment-round.form.label.finalModeFuturo" path="finalMode">
	<acme:form-option code="entrepreneur.investment-round.form.label.true" value="true"/> 
 	<acme:form-option code="entrepreneur.investment-round.form.label.false" value="false"/>
	</acme:form-select>

	</jstl:if>

	<acme:form-return code="entrepreneur.investment-round.form.button.return"/>
	
	<jstl:if test="${command == 'show' }">
	<button type="button" onclick="javascript: clearReturnUrl(); redirect('/entrepreneur/activity/list?id=${id}')"
            class="btn btn-default">
            <acme:message code="entrepreneur.investment-round.form.button.activities"/>
    </button>
    </jstl:if>	
    
    <jstl:if test="${command == 'show' }">
	<button type="button" onclick="javascript: clearReturnUrl(); redirect('/entrepreneur/activity/create?id=${id}')"
            class="btn btn-default">
            <acme:message code="entrepreneur.investment-record.form.button.crearactividad"/>
    </button>
    </jstl:if>
    
    <jstl:if test="${finalMode == 'false' }">
    <acme:form-submit test="${command == 'show' }"
	 code="entrepreneur.investment-round.form.button.update" 
	 action="/entrepreneur/investment-round/update"/>
	 
	 
	<acme:form-submit test="${command == 'update' }"
 	 code="entrepreneur.investment-round.form.button.update"
	 action="/entrepreneur/investment-round/update"/>
	</jstl:if> 
	
	<acme:form-submit test="${command == 'show' }"
	 code="entrepreneur.investment-round.form.button.delete" 
	 action="/entrepreneur/investment-round/delete"/>
	 
	 <acme:form-submit test="${command == 'delete' }"
	 code="entrepreneur.investment-round.form.button.delete" 
	 action="/entrepreneur/investment-round/delete"/>
	
	
	<jstl:if test="${command =='create'}">
	<acme:form-submit code="entrepreneur.investment-round.form.button.create" action="/entrepreneur/investment-round/create"/>
	</jstl:if>
	
</acme:form>
