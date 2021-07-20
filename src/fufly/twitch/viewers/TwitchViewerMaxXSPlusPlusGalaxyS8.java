package fufly.twitch.viewers;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class TwitchViewerMaxXSPlusPlusGalaxyS8 {
	
	private static ArrayList<Proxy> proxies;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		URL url = new URL("twitch url");
		proxies = new ArrayList<Proxy>();
		File proxiesFile = new File("./proxies.txt");
		if(!proxiesFile.exists()) {
			proxiesFile.createNewFile();
			System.out.println("Please add proxies into proxies.txt");
			Thread.sleep(3000);
			System.exit(1);
		}
		Scanner sc = new Scanner(proxiesFile);
		String proxiesString;
		try {
			proxiesString = sc.nextLine();
		} catch (Exception e) {
			proxiesString = "";
		}
		while(sc.hasNext()) {
			proxiesString += "\n" + sc.nextLine();
		}
		
		sc.close();
		
		if(proxiesString.equals("")) {
			System.out.println("Please add proxies into proxies.txt");
			Thread.sleep(3000);
			System.exit(1);
		}
		
		String[] allProxies = proxiesString.split("\n");
		for(String proxy : allProxies) {
			proxies.add(new Proxy(proxy));
		}
		
		for(int i = 0; i < proxies.size(); i++) {
			System.setProperty("http.proxyHost", proxies.get(i).getHost());
		    System.setProperty("http.proxyPort", proxies.get(i).getPort());
			Timer timer = new Timer();
			timer.reset();
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(!timer.hasReached(1000)) {
						try {
							HttpURLConnection connection = (HttpURLConnection) url.openConnection();
							connection.setRequestMethod("GET");
                            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.130 Safari/537.36");
                            connection.setRequestProperty("Content-Type", "application/json");
                            connection.setRequestProperty("Client-ID", "client id");
                            if(connection.getResponseCode() == 200 || connection.getResponseCode() == 202) {
                            	System.out.println("+1");
                            }else if(connection.getResponseCode() == 404) {
                            	System.out.println("404 Page not found");
                            }else if(connection.getResponseCode() == 403) {
                            	System.out.println("403 Forbidden");
                            }else if(connection.getResponseCode() == 401) {
                            	System.out.println("401 Proxy blocked");
                            }else {
                            	System.out.println(connection.getResponseCode() + " idk");
                            }
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}

}
