package com.sitech.basd.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.apache.log4j.Logger;


public class LogHome {
    private static Logger logger = Logger.getLogger(LogHome.class); 

    public static void  logExceptionStackTrace(Exception e){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream p = new PrintStream(os);
        e.printStackTrace(p);            
        logger.error(os.toString());       
    }
    
    public static void info(String info) {        
        logger.info(info);
    }
}