package acme.entities.application;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Application extends DomainEntity {
	
	// Serialisation identifier
	
		private static final long serialVersionUID = 1L;
			
	// Attributes
		
		@Column(unique = true)
		@NotBlank
		@Pattern(regexp = "^([A-Z]){3}[-]{1}[0-9]{2}[-]{1}[0-9]{6}$")
		private String ticker;
		
		
		@Temporal(TemporalType.TIMESTAMP)
		@NotNull
		@Past
		private Date dateOfCreation;
		
		@NotBlank
		@Length(min = 20)
		private String statement;
		
		@NotNull
		@Valid
		private Money moneyOffer;
		
		@NotNull
		private String aceptacion;
		
		private String justificacion;
		

	// Relationships
		
		@NotNull
		@Valid
		@ManyToOne(optional = false)
		private Investor investor;
		
		@NotNull
		@Valid
		@ManyToOne(optional = true)
		private InvestmentRound investment;
		
		
		
}
