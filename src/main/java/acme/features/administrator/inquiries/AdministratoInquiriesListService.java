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

package acme.features.administrator.inquiries;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inquiries.Inquiries;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratoInquiriesListService implements AbstractListService<Administrator, Inquiries> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorInquiriesRepository repository;


	

	@Override
	public boolean authorise(final Request<Inquiries> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Inquiries> request, final Inquiries entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		

		request.unbind(entity, model, "title", "dateOfCreation", "deadline");

	}
	

	@Override
	public Collection<Inquiries> findMany(final Request<Inquiries> request) {
		assert request != null;

		Collection<Inquiries> result;

		result = this.repository.findMany();
		
		return result;
	}

}
