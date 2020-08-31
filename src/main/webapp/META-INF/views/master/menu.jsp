<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.linkOscar" action="http://www.github.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.linkPablo" action="http://www.youtube.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.listShout" action="/anonymous/shout/list"/>
			<acme:menu-suboption code="master.menu.anonymous.createShout" action="/anonymous/shout/create"/>
			<acme:menu-suboption code="master.menu.anonymous.listDoradoBulletin" action="/anonymous/dorado-bulletin/list"/>
			<acme:menu-suboption code="master.menu.anonymous.createDoradoBulletin" action="/anonymous/dorado-bulletin/create"/>
			<acme:menu-suboption code="master.menu.anonymous.listCalleBulletin" action="/anonymous/calle-bulletin/list"/>
			<acme:menu-suboption code="master.menu.anonymous.createCalleBulletin" action="/anonymous/calle-bulletin/create"/>
			<acme:menu-suboption code="master.menu.anonymous.listNotice" action="/anonymous/notice/list"/>
			<acme:menu-suboption code="master.menu.anonymous.listToolRecord" action="/anonymous/tool-record/list"/>
			<acme:menu-suboption code="master.menu.anonymous.listTechnology-records" action="/anonymous/technology-records/list"/>
							
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.listNotice" action="/administrator/notice/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.createNotice" action="/administrator/notice/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.listInquiries" action="/administrator/inquiries/list" />
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.createInquiries" action="/administrator/inquiries/create" />
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.listOverture" action="/administrator/overture/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.createOverture" action="/administrator/overture/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.listTechnology-records" action="/administrator/technology-records/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.createTechnology-records" action="/administrator/technology-records/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.listToolRecord" action="/administrator/tool-record/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.createToolRecord" action="/administrator/tool-record/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.authenticated.listChallenges" action="/administrator/challenges/list" />
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.createChallenges" action="/administrator/challenges/create" />
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown"/>
			
		</acme:menu-option>

		
		<acme:menu-option code="master.menu.investor" access="hasRole('Investor')">
			<acme:menu-suboption code="master.menu.investor.listApplication" action="/investor/application/list-mine"/>
			<acme:menu-suboption code="master.menu.investor.listInvestment-round" action="/investor/investment-round/list"/>
			
			
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.entrepreneur" access="hasRole('Entrepreneur')"> 
			<acme:menu-suboption code="master.menu.entrepreneur.investment-round.list" action="/entrepreneur/investment-round/list-mine" />
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.createInvestmentRound" action="/entrepreneur/investment-round/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.entrepreneur.application.list" action="/entrepreneur/application/list-mine" />
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.entrepreneur.application.listGroup" action="/entrepreneur/application/list-group" />

		</acme:menu-option>
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-entrepreneur" action="/authenticated/entrepreneur/create" access="!hasRole('Entrepreneur')" />
			<acme:menu-suboption code="master.menu.user-account.entrepreneur" action="/authenticated/entrepreneur/update" access="hasRole('Entrepreneur')" />
			<acme:menu-suboption code="master.menu.user-account.become-investor" action="/authenticated/investor/create" access="!hasRole('Investor')"/>
			<acme:menu-suboption code="master.menu.user-account.investor" action="/authenticated/investor/update" access="hasRole('Investor')"/>
			<acme:menu-suboption code="master.menu.user-account.listNotice" action="/authenticated/notice/list"/>
			<acme:menu-suboption code="master.menu.user-account.listOverture" action="/authenticated/overture/list"/>
			<acme:menu-suboption code="master.menu.user-account.listToolRecord" action="/authenticated/tool-record/list"/>				
			<acme:menu-suboption code="master.menu.authenticated.listInquiries" action="/authenticated/inquiries/list" />
			<acme:menu-suboption code="master.menu.authenticated.listTechnology-records" action="/authenticated/technology-records/list"/>
			<acme:menu-suboption code="master.menu.authenticated.listChallenges" action="/authenticated/challenges/list" />
			<acme:menu-suboption code="master.menu.authenticated.listInvestmentRounds" action="/authenticated/investment-round/list" />
			
				
			
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

