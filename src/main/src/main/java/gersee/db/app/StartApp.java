package gersee.db.app;

import org.apache.commons.cli.CommandLine;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

import java.io.IOException;

/**
 * Starts the application
 *
 * Created by Gersee on 05.12.16.
 */
public class StartApp {

    private static Logger LOG = LogManager.getLogger(StartApp.class);
    private static Level LOGLEVEL = Level.INFO;

    public static void main(String[] args) {
        LOG.entry(args);

        //Change loglevel
        LoggerContext ctx;
        ctx = (LoggerContext) LogManager.getContext(false);
        Configuration config = ctx.getConfiguration();
        LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
        loggerConfig.setLevel(LOGLEVEL);
        ctx.updateLoggers();

        //Start reading
        CommandLineParsing clp = new CommandLineParsing();
        CommandLine cl = clp.parseArgs(args);
        StartupConfig conf = null;
        try {
            conf = clp.createConfByCommandline(cl);
        } catch (StartingArgumentsException e) {
            LOG.catching(e);
            System.exit(0);
        }

        LOG.debug("Start UUID-generator with config: " + conf.toString());

        UuidGenerator ug = new UuidGenerator(conf);
        try {
            ug.run();
        } catch (IOException e) {
            LOG.error("Writing error!");
            LOG.catching(e);
            System.exit(0);
        }

        LOG.exit();
    }
}
