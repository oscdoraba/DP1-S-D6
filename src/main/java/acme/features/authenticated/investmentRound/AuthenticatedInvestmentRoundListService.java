/*
 * AdministratorUserAccountListService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.investmentRound;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenges;
import acme.entities.inquiries.Inquiries;
import acme.entities.investmentRound.Activity;
import acme.entities.investmentRound.InvestmentRound;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedInvestmentRoundListService implements AbstractListService<Authenticated, InvestmentRound> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedInvestmentRoundRepository repository;


	

	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		

		request.unbind(entity, model, "ticker", "creation", "round", "title");

	}
	

	@Override
	public Collection<InvestmentRound> findMany(final Request<InvestmentRound> request) {
		assert request != null;

		List<Activity> actividadesActivas = (List<Activity>) this.repository.findActivitiesActives();
		
		List<InvestmentRound> activos = new ArrayList<InvestmentRound>();
		
		for (int i = 0; i < actividadesActivas.size(); i++) {
			Activity actividad = actividadesActivas.get(i);
			Integer idInvestment = actividad.getInvestment().getId();
			InvestmentRound investment = this.repository.findOne(idInvestment);
			activos.add(investment);
		}

		return activos.stream().distinct().collect(Collectors.toList());
	}

}
