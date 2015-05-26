package per.cz.upload;


/**
 * @author HJL
 *
 */
public interface Config {
	String PARAM_NAME="PATH";
	
	String PROP_PATH=Config.class.getClassLoader().getResource("upload.properties").getPath();
}
