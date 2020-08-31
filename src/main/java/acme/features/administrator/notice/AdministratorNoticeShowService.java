package acme.features.administrator.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.notice.Notice;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorNoticeShowService implements AbstractShowService<Administrator, Notice> {


	@Autowired
	private AdministratorNoticeRepository repository;


	@Override
	public boolean authorise(final Request<Notice> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Notice> request, final Notice entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture" ,"title", "creation", "deadline", "body", "optional1", "optional2");
	}

	@Override
	public Notice findOne(final Request<Notice> request) {
		assert request != null;

		Notice notice;
		int id;

		id = request.getModel().getInteger("id");
		notice = this.repository.findOneById(id);

		return notice;
	}

}