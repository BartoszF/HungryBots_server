package pl.bartoszf.hungry_bots;

import java.util.Random;

public final class Constants {

    public static final String MAX_THREADS = "max.threads";
    public static final String PORT_NUMBER = "port.num";
    public static final String STATUS = "status";
    public static final String DEFAULT_PORT_NUM = "12667";
    public static final String DEFAULT_MAX_THREADS = "120";
    public static final String CONFIG_FILE_ENV_PROPERTY = "config";
    public static final String CONFIG_FILE_NAME = "server.properties";
    public static final String FORCE_LOAD = "force.load";
    public static final String DB_USER = "db.user";
    public static final String DEFAULT_DB_USER = "admin";
    public static final String DB_PASS = "db.pass";
    public static final String DEFAULT_DB_PASS = "admin";
    public static final String DB_DATABASE = "db.db";
    public static final String DEFAULT_DB_DATABASE = "hungry_bots";
    public static final String DB_HOST = "db.host";
    public static final String DEFAULT_DB_HOST = "127.0.0.1";
    public static final String DB_PORT = "db.port";
    public static final String DEFAULT_DB_PORT = "27017";

    public static Random random = new Random();

}
