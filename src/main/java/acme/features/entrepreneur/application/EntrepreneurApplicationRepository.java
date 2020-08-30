/*
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes.  The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.entrepreneur.application;

import java.time.Month;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.application.Application;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurApplicationRepository extends AbstractRepository {

	// Listar aplicaciones, de las respectivas rondas de inversiones, agrupadas por su código bursátil. 
	@Query("select ap from Application ap where ap.investment.entrepreneur.id = ?1 order by ap.dateOfCreation")
	Collection<Application> findManyApplicationByEntrepreneurIdGrouped(Integer entrepreneurId);
	// Listar aplicaciones, de las respectivas rondas de inversiones, sin agrupar. 
	@Query("select ap from Application ap where ap.investment.entrepreneur.id = ?1")
	Collection<Application> findManyApplicationByEntrepreneurId(Integer entrepreneurId);
	
	
	@Query("select ap from Application ap where ap.id = ?1")
	Application findOne(Integer id);


}
