import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.XYSeries;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;


/**
 * Classe de creation de la courbe (repris de la documentation de jfreechart)
 *
 */
@SuppressWarnings("serial")
public class Courbe extends JFrame {

	/** le makespan de l'algo cts */
	int[] msCTS;

	/** le meilleur makespan. */
	int[] msIDEAL;

	
	public static int getMaxValue(int[] numbers){  
		int maxValue = numbers[0];  
		for(int i=1;i < numbers.length;i++){  
			if(numbers[i] > maxValue){  
				maxValue = numbers[i];  
			}  
		}  
		return maxValue;  
	}  
	/**
	 * Creer l'objet courbe utilisant jfreechart
	 *
	 * @param applicationTitle le titre de l'application
	 * @param chartTitle le titre du graphique
	 * @param tableau1 le premier tableau (la premiere courbe)
	 * @param tableau2 le second tableau (la deuxieme courbe)
	 */
	public Courbe(String applicationTitle, String chartTitle, int[] tableau1, int[] tableau2) {
		super(applicationTitle);

		msCTS = tableau1;
		msIDEAL = tableau2;
		double upperRange = getMaxValue(tableau1);

		// This will create the dataset 
		XYSeriesCollection dataset = createDataset();
		// based on the dataset we create the chart
		JFreeChart chart = createChart(dataset, chartTitle, upperRange*(1.1));
		// we put the chart into a panel
		ChartPanel chartPanel = new ChartPanel(chart);
		// default size
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		// add it to our application
		setContentPane(chartPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	/**
	 * Creation du set de données
	 *
	 * @return un objet XYSeriesCollection
	 */

	private  XYSeriesCollection createDataset() {

		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries xy = new XYSeries("CTS");
		XYSeries xy2 = new XYSeries("OPT");

		// Random rn = new Random();
		// XYPlot plot;

		for (int i = 0; i < 11; i++) {
			if(i!=0){
				xy.add((double) i/5., (double) msCTS[i]);
				xy2.add((double) i/5., (double) msIDEAL[i]);
			}
		}
		dataset.addSeries(xy);
		dataset.addSeries(xy2);

		return dataset;

	}


	/**
	 * Creation d'une courbe (D'apres la documentation de jfreechart)
	 *
	 * @param dataset La liste des données
	 * @param title Le titre
	 * @return l'objet jfreechart
	 */

	private JFreeChart createChart(XYSeriesCollection dataset, String title, double upperRange) {

		JFreeChart chart = ChartFactory.createXYLineChart(
				title, null, null, dataset, PlotOrientation.VERTICAL, true, true, false);

		XYPlot xyPlot = chart.getXYPlot();
		NumberAxis domainAxis = (NumberAxis) xyPlot.getDomainAxis();
		NumberAxis rangeAxis = (NumberAxis) xyPlot.getRangeAxis();

		domainAxis.setRange(0.2, 2.0);
		domainAxis.setTickUnit(new NumberTickUnit(0.2));
		rangeAxis.setRange(0.0, upperRange);
		rangeAxis.setTickUnit(new NumberTickUnit(500.0));

		chart.getXYPlot().setRenderer(new XYSplineRenderer());

		return chart;

	}
}
