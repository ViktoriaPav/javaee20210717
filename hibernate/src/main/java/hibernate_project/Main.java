package hibernate_project;

import hibernate_project.db.HibernateUTIL;
import hibernate_project.logic.MainLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        MainLogic mainLogic = new MainLogic();
        logger.debug("start");

        mainLogic.preparedInfo();
        logger.debug("loaded to DB");
        mainLogic.mainLogic();
        logger.debug("Finish");

        HibernateUTIL.shutdown();

    }
}
