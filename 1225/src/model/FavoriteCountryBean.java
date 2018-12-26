package model;

public class FavoriteCountryBean {
	private String ISO3166;
	private String Country;
	private String user;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getISO3166() {
		return ISO3166;
	}
	public void setISO3166(String iSO3166) {
		ISO3166 = iSO3166;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
}
