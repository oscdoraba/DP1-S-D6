package acme.entities.investmentRound;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Entrepreneur;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
	
public class Activity extends DomainEntity{
	
	// Serialisation identifier
		
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String title;
		
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	private Date start;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Future
	@NotNull
	private Date end;

	@NotNull
	@Valid
	private Money budget; //LA SUMA DE LOS BUDGET DE LAS ACTIVIDADES DE UN INVESTMENT DEBE SER IGUAL A SU AMOUNT
	
	
	// Relaciones
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private InvestmentRound		investment;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Entrepreneur	entrepreneur;
	
		

}
