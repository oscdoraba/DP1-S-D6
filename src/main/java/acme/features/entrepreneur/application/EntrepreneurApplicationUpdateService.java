package acme.features.entrepreneur.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.application.Application;
import acme.entities.overture.Overture;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurApplicationUpdateService implements AbstractUpdateService<Entrepreneur, Application> {


	@Autowired
	private EntrepreneurApplicationRepository repository;


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

		request.unbind(entity, model, "ticker", "dateOfCreation", "statement", "moneyOffer","aceptacion","justificacion");

	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		Application result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOne(id);

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String[] decisiones = entity.getAceptacion().split(","); //Al ser string si cogemos 2 decisiones, por ejemplo true y false, queda como: true,false
		entity.setAceptacion(decisiones[1].trim()); //Al partir dicho string por la "," podemos coger nuestra decision y setearla de forma correcta
												 // apareciendo de esta forma tan solo false
		
		boolean justificado = true;
		
		if (!errors.hasErrors("application")) {
			String decisionTomada = entity.getAceptacion();
			String justificacion = entity.getJustificacion();
			
			if(decisionTomada.equals("false") && (justificacion.isEmpty() || justificacion.equals(null))) {
				justificado = false;
			}
			
			errors.state(request, justificado, "justificacion", "entrepreneur.application.error.debeJustificar");
		}
	
	
	}												
	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity); 
	}

}