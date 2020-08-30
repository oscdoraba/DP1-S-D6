/*
 * AnonymousUserAccountRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes.  The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.investor.application;

import java.util.Collection;

import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.application.Application;
import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InvestorApplicationRepository extends AbstractRepository {

	@Query("select ap from Application ap where ap.investor.id = ?1")
	Collection<Application> findManyByInvestorId(Integer id);
	
	@Query("select ap from Application ap where ap.id = ?1")
	Application findOne(Integer id);
	
	@Query("select inv from Investor inv where inv.id = ?1")
	Investor findByInvestorId(int id);

	@Query("select ir from InvestmentRound ir where ir.id = ?1")
	InvestmentRound findInvestmentRoundById(int id);
	
	@Query("select a from Application a where a.ticker = ?1")
	Application findApplicationTicker(String ticker);



}
