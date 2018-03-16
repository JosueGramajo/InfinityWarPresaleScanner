import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Timer timer = new Timer();
		TimerTask myTask = new TimerTask() {
		    @Override
		    public void run() {
		        String cinepolisHTML = readURLContent("https://www.cinepolis.com.gt/proximos-estrenos");
		        if(!cinepolisHTML.equals("")){
		        	evaluateResponse(cinepolisHTML.toLowerCase(), "https://www.cinepolis.com.gt/proximos-estrenos");
		        }
		        
		        String cienpolisPreventasHTML = readURLContent("https://www.cinepolis.com.gt/preventas");
		        if(!cinepolisHTML.equals("")){
		        	evaluateResponse(cienpolisPreventasHTML.toLowerCase(), "https://www.cinepolis.com.gt/preventas");
		        }
		    }
		};

		timer.schedule(myTask, 120000, 120000);
	}
	public static String readURLContent(String url){
		System.out.println("Escanenado html de "+url+"...");
		String content = null;
		URLConnection connection = null;
		try {
		  connection =  new URL(url).openConnection();
		  Scanner scanner = new Scanner(connection.getInputStream());
		  scanner.useDelimiter("\\Z");
		  content = scanner.next();
		}catch ( Exception ex ) {
		    return "TIMEOUT";
		}
		return content;
	}
	public static void evaluateResponse(String html, String url){
		if(html.contains("infinity") || 
				html.contains("infinito") || 
				html.contains("vengadores") ||
				html.contains("avengers")){
			
			System.out.println("	YA SALIO LA PREVENTA DE ESA MIERDA!!!!!!!!!");
			
			JOptionPane.showMessageDialog(null, "ALA BERTA YA SALIO LA PREVENTA DE INFINITY WAR!!!!!");
			
	        String from = "InfinityWarNotification@gmail.com";
	        String pass = "InfinitySeraLaMera";
	        String[] to = { "jgramajo1997@gmail.com","batujedy.giorgio@gmail.com" }; // list of recipient email addresses
	        String subject = "PREVENTA INFINITY WAR";
	        String body = "Ya salio la preventa de infinity war en cinepolis!!! \n "+url;
	        
	        
	        EmailUtils.INSTANCE.sendFromGMail(from, pass, to, subject, body);
		}else{
			System.out.println("	Aun no ha salido la preventa :'c");
		}
	}
}
