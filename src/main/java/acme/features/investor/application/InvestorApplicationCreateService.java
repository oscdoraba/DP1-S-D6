
package acme.features.investor.application;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.application.Application;
import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class InvestorApplicationCreateService implements AbstractCreateService<Investor, Application> {

	// Internal state ---------------------------------------------------------

	@Autowired
	InvestorApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;
	
		return true;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "statement", "moneyOffer", "investor", "investment", "id");

		
	}

	@Override
	public Application instantiate(final Request<Application> request) {
		assert request != null;

		Application result;
		Date moment;
		

		result = new Application();
		int id = request.getModel().getInteger("id");
		
	    InvestmentRound ir= this.repository.findInvestmentRoundById(id);
		result.setInvestor(this.repository.findByInvestorId(request.getPrincipal().getActiveRoleId()));
		result.setInvestment(ir);
		
		moment = new Date(System.currentTimeMillis() - 1);
		result.setDateOfCreation(moment);
		
		result.setAceptacion("unanswered");

		return result;
		
		
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		boolean isCurrencyEuro;
		
		// Verificamos que no haya ningún duplicado:
		
		if (!errors.hasErrors("ticker")) {
			boolean isOnlyOne = this.repository.findApplicationTicker(entity.getTicker()) != null;
			errors.state(request, !isOnlyOne, "ticker", "investor.application.error.label.onlyOne");
		}

		// Comprobamos las divisas:
		
		if (!errors.hasErrors("moneyOffer")) { 
			String currency = entity.getMoneyOffer().getCurrency();
			isCurrencyEuro = currency.equals("€") || currency.equals("EUR");
			errors.state(request, isCurrencyEuro, "moneyOffer", "investor.application.error.euro-currency");
		}
		
		
		
	}

		

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;
		
				
		this.repository.save(entity);
		
	}

}
