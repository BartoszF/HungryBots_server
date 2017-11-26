package pl.bartoszf.hungry_bots.Configuration;

import pl.bartoszf.hungry_bots.Constants;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public enum ConfigurationManager {

    INSTANCE;
    private static Properties properties;
    private final Logger logger = Logger.getLogger(ConfigurationManager.class.getName());
    private static boolean FORCE_LOAD_PROPERTIES = Boolean.valueOf(System.getProperty(Constants.FORCE_LOAD));

    /**
     * Maximum number of threads getter.
     *
     * @return
     */
    public int getMaxThreads() {
        if (FORCE_LOAD_PROPERTIES || properties == null) {
            loadProperties();
        }
        return Integer.valueOf(properties.getProperty(Constants.MAX_THREADS, Constants.DEFAULT_MAX_THREADS));

    }

    /**
     * Port number getter.
     *
     * @return
     */
    public int getPort() {
        if (FORCE_LOAD_PROPERTIES || properties == null) {
            loadProperties();
        }
        return Integer.valueOf(properties.getProperty(Constants.PORT_NUMBER, Constants.DEFAULT_PORT_NUM));
    }

    /**
     * DB User getter.
     *
     * @return
     */
    public String getUser() {
        if (FORCE_LOAD_PROPERTIES || properties == null) {
            loadProperties();
        }
        return String.valueOf(properties.getProperty(Constants.DB_USER, Constants.DEFAULT_DB_USER));
    }

    /**
     * DB Pass getter.
     *
     * @return
     */
    public char[] getPass() {
        if (FORCE_LOAD_PROPERTIES || properties == null) {
            loadProperties();
        }
        return String.valueOf(properties.getProperty(Constants.DB_PASS, Constants.DEFAULT_DB_PASS)).toCharArray();
    }

    /**
     * DB getter.
     *
     * @return
     */
    public String getDB() {
        if (FORCE_LOAD_PROPERTIES || properties == null) {
            loadProperties();
        }
        return String.valueOf(properties.getProperty(Constants.DB_DATABASE, Constants.DEFAULT_DB_DATABASE));
    }

    public String getDBHost() {
        if (FORCE_LOAD_PROPERTIES || properties == null) {
            loadProperties();
        }
        return String.valueOf(properties.getProperty(Constants.DB_HOST, Constants.DEFAULT_DB_HOST));
    }

    public Integer getDBPort() {
        if (FORCE_LOAD_PROPERTIES || properties == null) {
            loadProperties();
        }
        return Integer.valueOf(properties.getProperty(Constants.DB_PORT, Constants.DEFAULT_DB_PORT));
    }

    /**
     * Load properties only once
     */
    private void loadProperties() {

        properties = new Properties();
        final String configPath = "resources/config.properties";
        if (configPath != null) {
            try {
                logger.info("Loading property file from external: " + configPath);
                properties.load(new FileInputStream(configPath));
            } catch (Exception e) {
                logger.warning("Failed to load property file from: " + configPath + ". Looking  in classpath.");
                loadFromClassPath();

            }
        } else {
            loadFromClassPath();
        }
    }

    /**
     * Loads property from class path.
     */
    private void loadFromClassPath() {
        try {
            InputStream in = ClassLoader.getSystemResourceAsStream("server.properties");
            if (in != null) {
                logger.info("Loading property file from class path");
                properties.load(in);
            }

        } catch (Exception e) {
            logger.warning("Failed to load property file from the class path. Default configurations will be used");
        }
    }

}
