package gersee.db.app;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Request command line parameters
 *
 * Created by Gersee on 05.12.16.
 */
public class CommandLineParsing {

    private Logger LOG = LogManager.getLogger(this.getClass());
    private final String OPTION_NUMBEROFUUID = "n";
    private final String OPTION_FILE = "f";

    /**
     * Parses the command line arguments.
     *
     * @param args The command line arguments to parse.
     * @return The parsed arguments.
     */
    public CommandLine parseArgs(String[] args) {
        LOG.entry(args);
        // create options
        Options options = new Options();

        Option o = new Option(OPTION_NUMBEROFUUID, "numberofuuid", true, "number of uuids to generate");
        o.setArgName("numberofuuid");
        o.setRequired(true);
        options.addOption(o);
        o = new Option(OPTION_FILE, "outputfile", true, "output-file to write the uuids");
        o.setArgName("outputfile");
        o.setRequired(true);
        options.addOption(o);

        options.addOption("h", "help", false, "show this help");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (Exception e) {
            LOG.error("Can't parse all required options to start application!");
            LOG.debug(e.getMessage(), e.getStackTrace());
        }
        // print help
        boolean error = args.length == 0 || cmd == null;
        if (error || cmd.hasOption("h")) {
            HelpFormatter formatter = new HelpFormatter();
            if (error)
                LOG.error("ERROR: Missing parameters!");
            formatter.printHelp("FileFormats", options, true);
            System.exit(0);
        }

        return LOG.exit(cmd);
    }

    /**
     * Creates a config object out of commandline-Input
     * @param cl CommandLine-Object
     * @return Config-Object
     */
    public StartupConfig createConfByCommandline(CommandLine cl) throws StartingArgumentsException {
        LOG.entry(cl);
        StartupConfig conf = new StartupConfig();

        conf.setFileName(cl.getOptionValue(OPTION_FILE));
        conf.setNumberOfUuids(Integer.valueOf(cl.getOptionValue(OPTION_NUMBEROFUUID)));

        if (conf.getFileName().length() < 1 || conf.getNumberOfUuids() <= 0) {
            throw new StartingArgumentsException("Arguments are not valid");
        }

        return LOG.exit(conf);
    }

}
