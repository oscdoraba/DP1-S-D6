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

package acme.features.entrepreneur.activity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.challenges.Challenges;
import acme.entities.investmentRound.Activity;
import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurActivityRepository extends AbstractRepository {

	@Query("select ac from Activity ac where ac.investment.id = ?1")
	Collection<Activity> findMany(Integer id);
	
	@Query("select ac from Activity ac where ac.id = ?1")
	Activity findOne(Integer id);
	
	@Query("select er from Entrepreneur er where er.id = ?1")
	Entrepreneur findOneEntrepreneurById(Integer id);
	
	@Query("select ir from InvestmentRound ir where ir.id = ?1")
	InvestmentRound findInvestmentRoundById(int id);
	
	//Suma budget actividades de una misma ronda de inversion
	@Query("select sum (budget.amount) from Activity ac where ac.investment.id = ?1")
	Double getSumaBudgetActividades(Integer InvestmentRoundId);


}
