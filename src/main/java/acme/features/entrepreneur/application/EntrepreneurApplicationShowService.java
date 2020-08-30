/*
 
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.entrepreneur.application;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.application.Application;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EntrepreneurApplicationShowService implements AbstractShowService<Entrepreneur, Application> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EntrepreneurApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;
		
		boolean result;
		int applicationId;
		Application ap;
		Entrepreneur e;
		Principal principal;
		
		applicationId = request.getModel().getInteger("id");
		ap = this.repository.findOne(applicationId);
		e = ap.getInvestment().getEntrepreneur();
		principal = request.getPrincipal();
		result = e.getUserAccount().getId() == principal.getAccountId();
		
		
		

		return result;
	}



	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "dateOfCreation", "statement", "moneyOffer","aceptacion","justificacion");

		
	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		Application result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOne(id);

		return result;
	}



	}



	


