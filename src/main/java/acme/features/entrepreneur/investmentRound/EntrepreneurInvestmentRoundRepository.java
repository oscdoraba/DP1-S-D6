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

package acme.features.entrepreneur.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.application.Application;
import acme.entities.challenges.Challenges;
import acme.entities.investmentRound.Activity;
import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurInvestmentRoundRepository extends AbstractRepository {

	@Query("select ir from InvestmentRound ir where ir.entrepreneur.id = ?1")
	Collection<InvestmentRound> findMany(Integer entrepreneurID);
	
	@Query("select ir from InvestmentRound ir where ir.id = ?1")
	InvestmentRound findOne(Integer id);
	
	@Query("select ac from Activity ac where ac.end > NOW()")
	Collection<Activity> findActivitiesActives();
	
	@Query("select ac from Activity ac where ac.investment.id = ?1")
	Collection<Activity> findManyActivitiesByInvestmentRoundID(Integer investmentRoundId);
	
	@Query("select ap from Application ap where ap.investment.entrepreneur.id = ?1")
	Collection<Application> findManyApplicationByEntrepreneurId(Integer entrepreneurId);
	
	@Query("select er from Entrepreneur er where er.id = ?1")
	Entrepreneur findOneEntrepreneurById(Integer id);
	
	//Suma budget actividades de una misma ronda de inversion
	@Query("select sum (budget.amount) from Activity ac where ac.investment.id = ?1")
	Double getSumaBudgetActividades(Integer InvestmentRoundId);
	
	//Contamos el numero de aplicaciones de un investment-round
	@Query("select count(*) from Application ap where ap.investment.id = ?1")
	Integer getNumeroAplicacionesByInvestmentRoundId(Integer investmentRoundId);

	//Contamos el numero de aplicaciones de un investment-round
	@Query("select count(*) from Activity ac where ac.investment.id = ?1")
	Integer getNumeroActividadesByInvestmentRoundId(Integer investmentRoundId);
}
