package acme.features.entrepreneur.investmentRound;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.doradoBulletin.DoradoBulletin;
import acme.entities.investmentRound.InvestmentRound;
import acme.entities.notice.Notice;
import acme.entities.overture.Overture;
import acme.entities.roles.Entrepreneur;
import acme.entities.shout.Shout;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurInvestmentRoundCreateService implements AbstractCreateService<Entrepreneur, InvestmentRound> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EntrepreneurInvestmentRoundRepository repository;


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

		request.unbind(entity, model, "ticker", "round", "title", "description", "amount", "optional");

		
	}

	@Override
	public InvestmentRound instantiate(final Request<InvestmentRound> request) {
		assert request != null;
		InvestmentRound result = new InvestmentRound();
		Date creation = new Date(System.currentTimeMillis() - 1);
		
		result.setCreation(creation);
		result.setFinalMode("false");
		result.setEntrepreneur(this.repository.findOneEntrepreneurById(request.getPrincipal().getActiveRoleId()));
		
		return result;
	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		boolean isCurrencyEuro;
		
		// Comprobamos las divisas:
		
				if (!errors.hasErrors("amount")) { 
					String currency = entity.getAmount().getCurrency();
					isCurrencyEuro = currency.equals("€") || currency.equals("EUR");
					errors.state(request, isCurrencyEuro, "amount", "entreprenur.investment-round.error.euro-currency");
				}
		

	}

		

	@Override
	public void create(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;

		
		this.repository.save(entity);
		
	}

}
