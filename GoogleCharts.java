import java.io.FileWriter;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Scanner;

	public class GoogleCharts {

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Digite a quantidade de números a serem gerados: ");
	        int numSamples = scanner.nextInt();

	        System.out.print("Digite o valor mínimo da amostra: ");
	        int minValue = scanner.nextInt();

	        System.out.print("Digite o valor máximo da amostra: ");
	        int maxValue = scanner.nextInt();

	        List<Integer> randomNumbers = generateRandomNumbers(numSamples, minValue, maxValue);
	        generateHtmlChart(randomNumbers);
	    }

	    public static List<Integer> generateRandomNumbers(int numSamples, int minValue, int maxValue) {
	        List<Integer> randomNumbers = new ArrayList<>();
	        for (int i = 0; i < numSamples; i++) {
	            int randomNumber = (int) (Math.random() * (maxValue - minValue + 1)) + minValue;
	            randomNumbers.add(randomNumber);
	        }
	        return randomNumbers;
	    }

	    public static void generateHtmlChart(List<Integer> data) {
	        try {
	            FileWriter htmlWriter = new FileWriter("random_numbers_chart.html");

	            htmlWriter.write("<html>\n<head>\n");
	            htmlWriter.write("<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\n");
	            htmlWriter.write("<script type=\"text/javascript\">\n");
	            htmlWriter.write("google.charts.load('current', {'packages':['corechart']});\n");
	            htmlWriter.write("google.charts.setOnLoadCallback(drawChart);\n");
	            htmlWriter.write("function drawChart() {\n");
	            htmlWriter.write("var data = new google.visualization.DataTable();\n");
	            htmlWriter.write("data.addColumn('number', 'Contador');\n");
	            htmlWriter.write("data.addColumn('number', 'Números Gerados');\n");
	            htmlWriter.write("data.addRows([\n");

	            for (int i = 0; i < data.size(); i++) {
	                htmlWriter.write("[" + (i + 1) + ", " + data.get(i) + "]");
	                if (i < data.size() - 1) {
	                    htmlWriter.write(",");
	                }
	            }

	            htmlWriter.write("]);\n");

	            htmlWriter.write("var options = {'title':'Gráfico de Linha de Números Aleatórios',");
	            htmlWriter.write("'width':800, 'height':600};\n");

	            htmlWriter.write("var chart = new google.visualization.LineChart(document.getElementById('chart_div'));\n");
	            htmlWriter.write("chart.draw(data, options);\n");
	            htmlWriter.write("}\n");
	            htmlWriter.write("</script>\n");
	            htmlWriter.write("</head>\n<body>\n");
	            htmlWriter.write("<div id=\"chart_div\"></div>\n");
	            htmlWriter.write("</body>\n</html>");

	            htmlWriter.close();
	            System.out.println("Arquivo HTML gerado com sucesso.");

	        } catch (IOException e) {
	            System.err.println("Erro ao criar o arquivo HTML: " + e.getMessage());
	        }
	    }
	}
