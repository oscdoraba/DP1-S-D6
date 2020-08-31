package acme.features.entrepreneur.investmentRound;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRound.InvestmentRound;
import acme.entities.overture.Overture;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurInvestmentRoundUpdateService implements AbstractUpdateService<Entrepreneur, InvestmentRound> {


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
		
		Boolean esSpamTitulo = false, esSpamDescripcion = false, sumaIncorrecta = false , esfinalMode = false;
		boolean isCurrencyEuro;
		Integer threshold = 2; //El numero de palabras de spam en los campos titulo y descripcion no puede superar este entero (Limite)
		Integer palabrasSpamTitulo= 0;
		Integer palabrasSpamDescripcion= 0;
		List<String> spamWords = new ArrayList<>();
		spamWords.add("sex");
		spamWords.add("hard core");
		spamWords.add("viagra");
		spamWords.add("cialis");
		spamWords.add("nigeria");
		spamWords.add("you've won");
		spamWords.add("million dollar");
		String[] modosFinales = entity.getFinalMode().split(",");
		entity.setFinalMode(modosFinales[1]);
		
		//TITULO SPAM
		//-----------------------------------------------------------------------------------------------------

		if (!errors.hasErrors("investment-round")) {

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
			if(palabrasSpamTitulo > threshold && entity.getFinalMode().equals("true")) {
				esSpamTitulo=true;
			}
			errors.state(request, !esSpamTitulo, "title", "entrepreneur.investment-round.error.titleSpam");
			
		}
		
		//DESCRIPCION SPAM
		//-----------------------------------------------------------------------------------------------------

		
		if (!errors.hasErrors("investment-round")) {

			String[] palabras = entity.getDescription().split(" ");
			for (int i = 0; i < palabras.length; i++) {
				for (String w : spamWords) {
					if (w.trim().equals(palabras[i].trim())) {
						palabrasSpamDescripcion++;
					}
				}

				if (i <= palabras.length - 2) {
					if (palabras[i].equals("hard") && palabras[i + 1].equals("core")) {
						palabrasSpamDescripcion++;
					} else if (palabras[i].equals("you've") && palabras[i + 1].equals("won")) {
						palabrasSpamDescripcion++;
					} else if (palabras[i].equals("million") && palabras[i + 1].equals("dollar")) {
						palabrasSpamDescripcion++;
					}
				}

			}
			
			if(palabrasSpamDescripcion > threshold && entity.getFinalMode().equals("true")) {
				esSpamDescripcion=true;
			}

			errors.state(request, !esSpamDescripcion, "description", "entrepreneur.investment-round.error.descriptionSpam");
			
		}
		
		//No puedes actualizar si la suma de los budget de las actividades no tiene el mismo valor que el amount de dicha ronda de inversion
		//---------------------------------------------------------------------------------------------------------------------
		
		if (!errors.hasErrors("investment-round")) {
			Integer investmentRoundId = entity.getId();
			Double sumaBudgetActividades = 0.;
			Integer numeroActividades = this.repository.getNumeroActividadesByInvestmentRoundId(investmentRoundId);
			if(numeroActividades >0) {
			sumaBudgetActividades = this.repository.getSumaBudgetActividades(investmentRoundId);
			}

			if(!sumaBudgetActividades.equals(entity.getAmount().getAmount()) && entity.getFinalMode().equals("true")) { 
				sumaIncorrecta=true;						//Es raro pero investment tiene un atributo Money amount y a su vez,	
															// esta clase contiene un atributo Double amount
			}
			
			errors.state(request, !sumaIncorrecta, "amount", "entrepreneur.investment-round.error.sumaMal");
		}
		
		// Comprobamos las divisas:
		
				if (!errors.hasErrors("amount")) { 
					String currency = entity.getAmount().getCurrency();
					isCurrencyEuro = currency.equals("€") || currency.equals("EUR");
					errors.state(request, isCurrencyEuro, "amount", "entreprenur.investment-round.error.euro-currency");
				}
		


	}
	@Override
	public void update(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}

}