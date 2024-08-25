package telran.cars.dto;

import static telran.cars.api.RentCompanyErrorMessage.*;

import java.io.Serializable;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class Model implements Serializable
{
	private static final long serialVersionUID = 3629860314527912835L;

	@NotBlank(message = MODEL_NAME_IS_BLANK)
	private String modelName;
	
	@Min(value = 50, message = MIN_GAS_TANK)
	@Max(value = 500, message = MAX_GAS_TANK)
	private int gasTank;
	
	@NotBlank(message = COMPANY_IS_BLANK)
	@Pattern(regexp = "[A-Z][A-Za-z]*", message = COMPANY_WRONG_PATTERN)
	private String company;
	
	@NotBlank(message = COUNTRY_IS_BLANK)
	@Pattern(regexp = "[A-Z][a-z]*", message = COUNTRY_WRONG_PATTERN)
	private String country;
	
	@Positive(message = WRONG_PRICE_DAY)
	private int priceDay;
	
	public Model()
	{
	}

	public Model(String modelName, int gasTank, String company, String country, int priceDay)
	{
		super();
		this.modelName = modelName;
		this.gasTank = gasTank;
		this.company = company;
		this.country = country;
		this.priceDay = priceDay;
	}

	public int getPriceDay()
	{
		return priceDay;
	}

	public void setPriceDay(int priceDay)
	{
		this.priceDay = priceDay;
	}

	public String getModelName()
	{
		return modelName;
	}

	public int getGasTank()
	{
		return gasTank;
	}

	public String getCompany()
	{
		return company;
	}

	public String getCountry()
	{
		return country;
	}

	@Override
	public String toString()
	{
		return "Model [modelName=" + modelName + ", gasTank=" + gasTank + ", company=" + company + ", country="
				+ country + ", priceDay=" + priceDay + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((modelName == null) ? 0 : modelName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!(obj instanceof Model))
			return false;
		Model other = (Model) obj;
		if (modelName == null)
		{
			if (other.modelName != null)
				return false;
		} else if (!modelName.equals(other.modelName))
			return false;
		return true;
	}
}
