package per.cz.timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class OnLineListener implements ServletContextListener {
  
	@SuppressWarnings("unused")
	private Logger log =LoggerFactory.getLogger(OnLineListener.class);  
//	private Timer timer;
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent arg0) {
		log.info("启动阅读器");
		
	}
}
