
public class SalesPerson {

	private String name;
	private int totalSales;
	private int salesPeriod;
	private float experienceMultiplier;
	private float score;

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public SalesPerson(String name, int totalSales, int salesPeriod, float experienceMultiplier, float score) {
		super();
		this.name = name;
		this.totalSales = totalSales;
		this.salesPeriod = salesPeriod;
		this.experienceMultiplier = experienceMultiplier;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(int totalSales) {
		this.totalSales = totalSales;
	}

	public int getSalesPeriod() {
		return salesPeriod;
	}

	public void setSalesPeriod(int salesPeriod) {
		this.salesPeriod = salesPeriod;
	}

	public float getExperienceMultiplier() {
		return experienceMultiplier;
	}

	public void setExperienceMultiplier(float experienceMultiplier) {
		this.experienceMultiplier = experienceMultiplier;
	}

}
