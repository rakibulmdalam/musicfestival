package de.tum.in.dbpra.model.bean;

public class ProductBean {
	
	private Integer id;
	private String name;
	private String type; // TODO use enum
	private Double price;
	private ProviderBean provider;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public ProviderBean getProvider() {
		return provider;
	}

	public void setProvider(ProviderBean provider) {
		this.provider = provider;
	}
}
