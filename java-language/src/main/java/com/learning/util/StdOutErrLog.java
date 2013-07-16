package com.learning.util;

import java.io.PrintStream;

import org.apache.log4j.Logger;

/**
 * 
 * @author zhengzh
 *
 */
public class StdOutErrLog {

    private static final Logger logger = Logger.getLogger(StdOutErrLog.class);
    
    /**
     * tie SystemOut And Err To Log 
     */
    public static void tieSystemOutAndErrToLog() {
        System.setOut(createLoggingProxy(System.out));
        System.setErr(createLoggingProxy(System.err));
    }
    
    /**
     * 
     * @param realPrintStream
     * @return
     */
    private static PrintStream createLoggingProxy(final PrintStream realPrintStream) {
        return new PrintStream(realPrintStream) {
            public void print(final String string) {
                realPrintStream.print(string + "\r\n");
            	logger.info(string);
            }
        };
    }
}