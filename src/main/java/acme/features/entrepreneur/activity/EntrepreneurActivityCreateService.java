package acme.features.entrepreneur.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.doradoBulletin.DoradoBulletin;
import acme.entities.investmentRound.Activity;
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
public class EntrepreneurActivityCreateService implements AbstractCreateService<Entrepreneur, Activity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EntrepreneurActivityRepository repository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "end", "budget","entrepreneur","investment","id");

		
	}

	@Override
	public Activity instantiate(final Request<Activity> request) {
		assert request != null;
		Activity result;
		result = new Activity();
		Integer idInvestment = request.getModel().getInteger("id");
		InvestmentRound investment = this.repository.findInvestmentRoundById(idInvestment);
		Date start = new Date(System.currentTimeMillis() - 1);
		
		result.setStart(start);
		result.setEntrepreneur(this.repository.findOneEntrepreneurById(request.getPrincipal().getActiveRoleId()));
		result.setInvestment(investment);

		
		return result;
	}

	@Override
	public void validate(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		Boolean esSpamTitulo = false, sePasaBudget = false;
		boolean isCurrencyEuro;
		Integer threshold = 2; //El numero de palabras de spam en los campos titulo y descripcion no puede superar este entero (Limite)
		Integer palabrasSpamTitulo= 0;
		List<String> spamWords = new ArrayList<>();
		spamWords.add("sex");
		spamWords.add("hard core");
		spamWords.add("viagra");
		spamWords.add("cialis");
		spamWords.add("nigeria");
		spamWords.add("you've won");
		spamWords.add("million dollar");
		
		//TITULO SPAM
		//-----------------------------------------------------------------------------------------------------

		if (!errors.hasErrors("activity")) {

			String[] palabras = entity.getTitle().split(" ");
			for (int i = 0; i < palabras.length; i++) {
				for (String w : spamWords) {
					if (w.trim().equals(palabras[i].trim())) {
						palabrasSpamTitulo++;
					}
				}

				if (i <= palabras.length - 2) {
					if (palabras[i].equals("hard") && palabras[i + 1].equals("core")) {
						palabrasSpamTitulo++;
					} else if (palabras[i].equals("you've") && palabras[i + 1].equals("won")) {
						palabrasSpamTitulo++;
					} else if (palabras[i].equals("million") && palabras[i + 1].equals("dollar")) {
						palabrasSpamTitulo++;
					}
				}

			}
			if(palabrasSpamTitulo > threshold) {
				esSpamTitulo=true;
			}
			errors.state(request, !esSpamTitulo, "title", "entrepreneur.activity.error.titleSpam");
			
		}
		
		// Comprobamos las divisas:
		
				if (!errors.hasErrors("amount")) { 
					String currency = entity.getBudget().getCurrency();
					isCurrencyEuro = currency.equals("€") || currency.equals("EUR");
					errors.state(request, isCurrencyEuro, "budget", "entreprenur.actvity.error.euro-currency");
				}


	}

		

	@Override
	public void create(final Request<Activity> request, final Activity entity) {
		assert request != null;
		assert entity != null;
		
		
		this.repository.save(entity);
		
	}

}
