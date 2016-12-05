package gersee.db.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Config for startup
 * Created by Gersee on 05.12.16.
 */
public class StartupConfig {

    private Logger LOG = LogManager.getLogger(this.getClass());

    private String fileName;
    private int numberOfUuids;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getNumberOfUuids() {
        return numberOfUuids;
    }

    public void setNumberOfUuids(int numberOfUuids) {
        this.numberOfUuids = numberOfUuids;
    }


    @Override
    public String toString() {
        return gersee.db.app.GenericHelper.getToString(this);
    }
}
