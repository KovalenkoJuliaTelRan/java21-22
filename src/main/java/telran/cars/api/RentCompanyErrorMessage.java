package telran.cars.api;

public interface RentCompanyErrorMessage
{
	String DATE_IS_NULL = "Date can't be null";
	String DATE_IS_NOT_PAST = "Date must be in the past";
	String DATE_WRONG_FORMAT = "Wrong date format";
	
	String AGE_LESS_THAN_MIN = "Age must be greate or equals than 16";
	String AGE_GREATER_THAN_MAX = "Age must be less or equals than 80";
	
	String TYPE_MISMATCH = "Url parameter has type mismatch";
	String JSON_TYPE_MISMATCH = "JSON contains field with type mismatch";
	
	String REG_NUMBER_IS_NULL = "Registration number can't be null";
	String REG_NUMBER_WRONG_PATTERN = "Wrong pattern of registration number";
	
	String COLOR_IS_BLANK = "Color can't be null or empty";
	
	String MODEL_NAME_IS_BLANK = "Model name can't be null or empty";
	
	String MIN_GAS_TANK = "Gas tank can't be less than 50";
	String MAX_GAS_TANK = "Gas tank can't be more than 500";
	
	String COMPANY_IS_BLANK = "Company can't be null or empty";
	String COMPANY_WRONG_PATTERN = "Wrong pattern of company";
	
	String COUNTRY_IS_BLANK = "Country can't be null or empty";
	String COUNTRY_WRONG_PATTERN = "Wrong pattern of country";

	String WRONG_PRICE_DAY = "Price per day must be a posotive number";
}
