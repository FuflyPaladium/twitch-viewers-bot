package fufly.twitch.viewers;

public class Proxy {
	
	private String proxyHost;
	private String proxyPort;
	
	public Proxy(String proxy) {
		proxyHost = proxy.split(":")[0];
		proxyPort = proxy.split(":")[1];
	}
	
	public String getHost() {
		return proxyHost;
	}
	
	public String getPort() {
		return proxyPort;
	}

}
