package acme.features.administrator.overture;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.doradoBulletin.DoradoBulletin;
import acme.entities.notice.Notice;
import acme.entities.overture.Overture;
import acme.entities.shout.Shout;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorOvertureCreateService implements AbstractCreateService<Administrator, Overture> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorOvertureRepository repository;


	@Override
	public boolean authorise(final Request<Overture> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Overture> request, final Overture entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Overture> request, final Overture entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline","description","minMoney","maxMoney","email");

		
	}

	@Override
	public Overture instantiate(final Request<Overture> request) {
		assert request != null;
		Overture overture = new Overture();
		Date creation;
		
		creation = new Date(System.currentTimeMillis() - 1);
		overture.setCreation(creation);
		
		return overture;
	}

	@Override
	public void validate(final Request<Overture> request, final Overture entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		boolean isCurrencyMinEuro, isCurrencyEuroMaxEuro, isMinMax;

		// Comprobamos las divisas:
		
		if (!errors.hasErrors("minMoney")) { 
			String currencyMin = entity.getMinMoney().getCurrency();
			isCurrencyMinEuro = currencyMin.equals("€") || currencyMin.equals("EUR");
			errors.state(request, isCurrencyMinEuro, "minMoney", "administrator.overture.error.min-euro-currency");
		}

		if (!errors.hasErrors("maxMoney")) {
			String currencyMax = entity.getMaxMoney().getCurrency();
			isCurrencyEuroMaxEuro = currencyMax.equals("€") || currencyMax.equals("EUR");
			errors.state(request, isCurrencyEuroMaxEuro, "maxMoney", "administrator.overture.error.max-euro-currency");
		}

		// El máximo del intervalo del dinero debe ser mayor que el mínimo:
		if (!errors.hasErrors("maxMoney") && !errors.hasErrors("minMoney")) {
			Double minAmount = entity.getMinMoney().getAmount();
			Double maxAmount = entity.getMaxMoney().getAmount();
			isMinMax = minAmount < maxAmount;
			errors.state(request, isMinMax, "maxMoney", "administrator.overture.error.not-max");
		}


	}

		

	@Override
	public void create(final Request<Overture> request, final Overture entity) {
		assert request != null;
		assert entity != null;

		
		this.repository.save(entity);
		
	}

}
