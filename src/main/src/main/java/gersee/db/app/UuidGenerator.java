package gersee.db.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Gersee on 05.12.16.
 */
public class UuidGenerator {

    private Logger LOG = LogManager.getLogger(this.getClass());

    private StartupConfig sc;

    public UuidGenerator(StartupConfig sc) {
        LOG.entry(sc);
        this.sc = sc;
        LOG.exit();
    }

    //Starts generation and writing of UUIDs
    public void run() throws IOException{
        LOG.entry(sc);
        BufferedWriter writer = new BufferedWriter(new FileWriter(sc.getFileName()));

        LOG.info("initialize writer complete - will generate " + sc.getNumberOfUuids() + " UUIDs");

        for (int i=0; i<sc.getNumberOfUuids(); i++) {
            writer.append(UUID.randomUUID().toString());
            writer.newLine();
            if (i % 100 == 0) {
                writer.flush();
                LOG.info("Write last 100 UUIDs - overall generated until now were " + i + " UUIDs.");
            }
        }

        LOG.info("Wrote all " + sc.getNumberOfUuids() + " UUIDs");
        writer.flush();
        writer.close();
        LOG.debug("Closed writer");
        LOG.exit();
    }
}
