package acme.features.entrepreneur.investmentRound;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.application.Application;
import acme.entities.investmentRound.Activity;
import acme.entities.investmentRound.InvestmentRound;
import acme.entities.overture.Overture;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractDeleteService;

@Service
public class EntrepreneurInvestmentRoundDeleteService implements AbstractDeleteService<Entrepreneur, InvestmentRound> {

	@Autowired
	private EntrepreneurInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,  "ticker", "creation", "round", "title", "description", "amount", "optional","finalMode");
	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		assert request != null;

		InvestmentRound result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOne(id);

		return result;
	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		Boolean hayAplicaciones = false;
		
		if (!errors.hasErrors("investment-round")) {
			Integer investmentRoundId = entity.getId();
			Integer numeroAplicaciones = this.repository.getNumeroAplicacionesByInvestmentRoundId(investmentRoundId);
			
			if(numeroAplicaciones > 0) {
				hayAplicaciones = true;
			}
			
			errors.state(request, !hayAplicaciones, "ticker", "entrepreneur.investment-round.error.hayAplicaciones");
		}


	}

	@Override
	public void delete(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;
		
		Integer entrepreneurId = request.getPrincipal().getActiveRoleId();
		Integer investmentRoundId = request.getModel().getInteger("id");
		Collection<Application> aplicaciones = this.repository.findManyApplicationByEntrepreneurId(entrepreneurId);
		Collection<Activity> actividades = this.repository.findManyActivitiesByInvestmentRoundID(investmentRoundId);
		
		for(Application ap: aplicaciones) {
			this.repository.delete(ap);
		}
		
		for(Activity ac: actividades) {
			this.repository.delete(ac);
		}

		this.repository.delete(entity);
	}

}