/**
 *
 * @author GILAD MOLEK
 * @author DORON TUCHMAN
 * @author MATI HALFA
 * @author MATAN ASULIN
 * @author SHARONE BURSHTIEN
 *
 *	@version 1.40
 *	@since 2019
 *
 * Log File maintaining class
 */
package Logger;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogFile {
	private  String filePath="../gcm/src/Logfile/serverLogFile.log";
	private FileHandler fh;
	private Logger logger =Logger.getLogger("MyLog");

	/**
	 * creation of log file
	 */
	public LogFile()
	{
        //just to make our log file nicer :)
        SimpleDateFormat format = new SimpleDateFormat("M-d_HHmmss");
        try {
            fh = new FileHandler("../gcm/src/Logfile/serverLogFile.log");
        } catch (Exception e) {
            e.printStackTrace();
        }

        fh.setFormatter(new SimpleFormatter());
        logger.addHandler(fh);
	}
	/**
	 * adding String text to the log file
	 * @param text
	 */
	public void logging(String text)
	{
		logger.setUseParentHandlers(false);
	    logger.info(text);
	}



}
