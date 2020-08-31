package acme.entities.investmentRound;

import java.util.Date;

import javax.persistence.Column;
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
import javax.validation.constraints.Pattern;

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
	
public class InvestmentRound extends DomainEntity{
	
	// Serialisation identifier
		
	private static final long serialVersionUID = 1L;
		
	@Column(unique = true)	
	@NotBlank
	@Pattern(regexp = "^([A-Z]){3}[-]{1}[0-9]{2}[-]{1}[0-9]{6}$")
	private String ticker;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	private Date creation;
	
	@NotNull
	private KindRound round;
	
	@NotBlank
	private String title;
	
	@NotBlank
	@Length(min = 20)
	private String description;
		
	@NotNull
	@Valid
	private Money amount;
	
	@URL
	private String optional;
	
	@NotNull
	private String finalMode;
	
	
	// Relaciones
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Entrepreneur	entrepreneur;
	
}
