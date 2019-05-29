import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class ReportGenerator {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please, insert the filepath to the salespeople data file:");
		String filePathOfSalesPersons = sc.nextLine();
		System.out.println("Please, insert the filepath to the report definition file:");
		String filePathOfReportDefinition = sc.nextLine();
		sc.close();

		List<SalesPerson> salesPersons = new ArrayList<SalesPerson>();
		ReportDefinition[] report = new ReportDefinition[1];
		List<Float> sortedScores = new ArrayList<>();
		int lowerLimit = 0;

		try {
			JsonReader jr2 = new JsonReader(new FileReader(filePathOfReportDefinition));
			report[0] = new Gson().fromJson(jr2, ReportDefinition.class);

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			JsonReader jr = new JsonReader(new FileReader(filePathOfSalesPersons));
			SalesPerson[] temp = new Gson().fromJson(jr, SalesPerson[].class);

			for (int i = 0; i < temp.length; i++) {
				if (temp[i].getSalesPeriod() <= report[0].getPeriodLimit()) {
					float tempScore;
					if (report[0].isUseExperienceMultiplier() == true) {
						tempScore = temp[i].getTotalSales() / temp[i].getSalesPeriod()
								* temp[i].getExperienceMultiplier();
					} else {
						tempScore = temp[i].getTotalSales() / temp[i].getSalesPeriod();
					}
					salesPersons.add(new SalesPerson(temp[i].getName(), temp[i].getTotalSales(),
							temp[i].getSalesPeriod(), temp[i].getExperienceMultiplier(), tempScore));
				} else {

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < salesPersons.size(); i++) {
			sortedScores.add(salesPersons.get(i).getScore());
		}
		Collections.sort(sortedScores);
		Collections.reverse(sortedScores);

		lowerLimit = (int) (((float) report[0].getTopPerformersThreshold() / 100) * sortedScores.size());

		FileWriter csvWriter = new FileWriter("D:\\Report.txt");
		csvWriter.append("Name    , Score" + "\n");
		for (int i = 0; i < salesPersons.size(); i++) {
			if (lowerLimit != 0 && salesPersons.get(i).getScore() <= sortedScores.get(0)
					&& salesPersons.get(i).getScore() >= sortedScores.get(lowerLimit - 1)) {
				csvWriter.append(salesPersons.get(i).getName() + ", " + salesPersons.get(i).getScore() + "\n");

			} else if (lowerLimit == 0 && salesPersons.get(i).getScore() == sortedScores.get(0)) {
				csvWriter.append(salesPersons.get(i).getName() + ", " + salesPersons.get(i).getScore() + "\n");
			}
		}
		csvWriter.flush();
		csvWriter.close();
		System.out.println("The report file - Report.txt is created in D:\\");
	}

}
