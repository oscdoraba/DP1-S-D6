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

package acme.features.administrator.notice;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.stream.Collectors;

import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.notice.Notice;
import acme.entities.shout.Shout;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorNoticeListService implements AbstractListService<Administrator, Notice> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorNoticeRepository repository;


	// AbstractListService<Administrator, UserAccount> interface --------------

	@Override
	public boolean authorise(final Request<Notice> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Notice> request, final Notice entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		

		request.unbind(entity, model, "picture", "creation", "deadline");

	}
	

	@Override
	public Collection<Notice> findMany(final Request<Notice> request) {
		assert request != null;

		Collection<Notice> result;

		result = this.repository.findMany();
		//result = result.stream().filter(x -> x.getDeadline()> new Date()).collect(Collectors.toCollection(LinkedList::new));
		
		return result;
	}

}
