/* ====================================== */
/* Copyright (c) 2004 Unisys Corporation. */
/*          All rights reserved.          */
/*          UNISYS CONFIDENTIAL           */
/* ====================================== */

package com.unisys.trans.shared.util.logging;

import com.unisys.trans.shared.security.constant.*;
import com.unisys.trans.shared.security.service.*;
import com.unisys.trans.shared.util.constant.*;
import com.unisys.trans.shared.util.param.*;
import com.unisys.trans.shared.util.response.*;

import java.util.logging.*;
import java.util.*;

/**
 * This class does not involve LoggingMonitor.
 */
public class SimpleLogger {

    /**
     * Processes fatal event.
     */
    public static void fatal( int pLogId, String pMessage ) {
        processEvent( SharedLevel.FATAL, pLogId, pMessage );
    }

    /**
     * Processes fatal event.
     */
    public static void fatal( int pLogId, Throwable pThrowable, String pMessage ) {
        processEvent( SharedLevel.FATAL, pLogId, pThrowable, pMessage );
    }

    /**
     * Processes fatal event.
     */
    public static void fatal( int pLogId, ResponseData pResponseData ) {
        processEvent( SharedLevel.FATAL, pLogId, pResponseData );
    }

    /**
     * Processes fatal event.
     */
    public static void fatal( int pLogId, Throwable pThrowable, ResponseData pResponseData ) {
        processEvent( SharedLevel.FATAL, pLogId, pThrowable, pResponseData );
    }

    /**
     * Processes error event.
     */
    public static void error( int pLogId, String pMessage ) {
        processEvent( SharedLevel.ERROR, pLogId, pMessage );
    }

    /**
     * Processes error event.
     */
    public static void error( int pLogId, Throwable pThrowable, String pMessage ) {
        processEvent( SharedLevel.ERROR, pLogId, pThrowable, pMessage );
    }

    /**
     * Processes error event.
     */
    public static void error( int pLogId, ResponseData pResponseData ) {
        processEvent( SharedLevel.ERROR, pLogId, pResponseData );
    }

    /**
     * Processes error event.
     */
    public static void error( int pLogId, Throwable pThrowable, ResponseData pResponseData ) {
        processEvent( SharedLevel.ERROR, pLogId, pThrowable, pResponseData );
    }

    /**
     * Processes warning event.
     */
    public static void warning( String pMessage ) {

        // Initialize a new log record
        LogRecord logRecord = getLogRecord( SharedLevel.WARNING, -1 );
        logRecord.setMessage( pMessage );

        // Log the event
        java.util.logging.Logger.getLogger( logRecord.getSourceClassName() ).log( logRecord );
    }

    /**
     * Processes info event.
     */
    public static void info( String pMessage ) {

        // Initialize a new log record
        LogRecord logRecord = getLogRecord( SharedLevel.INFO, -1 );
        logRecord.setMessage( pMessage );

        // Log the event
        java.util.logging.Logger.getLogger( logRecord.getSourceClassName() ).log( logRecord );
    }

    /**
     * Processes debug event.
     */
    public static void debug( String pMessage ) {

        // Initialize a new log record
        LogRecord logRecord = getLogRecord( SharedLevel.DEBUG, -1 );
        logRecord.setMessage( pMessage );

        // Log the event
        java.util.logging.Logger.getLogger( logRecord.getSourceClassName() ).log( logRecord );
    }

    /**
     * Processes debug event.
     */
    public static void debug( Throwable pThrowable, String pMessage ) {

        // Initialize a new log record
        LogRecord logRecord = getLogRecord( SharedLevel.DEBUG, -1 );
        logRecord.setMessage( pMessage );
        logRecord.setThrown( pThrowable );

        // Log the event
        java.util.logging.Logger.getLogger( logRecord.getSourceClassName() ).log( logRecord );
    }

    /**
     * Common code for event processing.
     */
    protected static void processEvent( SharedLevel pLevel, int pLogId, String pMessage ) {

        // Create additional parameters for the log record
        Object[] parameters = new Object[] { getResponseDataHolder( pMessage ) };

        // Initialize a new log record
        LogRecord logRecord = getLogRecord( pLevel, pLogId );
        logRecord.setParameters( parameters );

        // Log the event
        java.util.logging.Logger.getLogger( logRecord.getSourceClassName() ).log( logRecord );
    }

    /**
     * Common code for event processing.
     */
    protected static void processEvent( SharedLevel pLevel, int pLogId, Throwable pThrowable, String pMessage ) {

        // Create additional parameters for the log record
        Object[] parameters = new Object[] { getResponseDataHolder( pMessage ) };

        // Initialize a new log record
        LogRecord logRecord = getLogRecord( pLevel, pLogId );
        logRecord.setParameters( parameters );
        logRecord.setThrown( pThrowable );

        // Log the event
        java.util.logging.Logger.getLogger( logRecord.getSourceClassName() ).log( logRecord );
    }

    /**
     * Common code for event processing.
     */
    protected static void processEvent( SharedLevel pLevel, int pLogId, ResponseData pResponseData ) {

        // Create additional parameters for the log record
        Object[] parameters = new Object[] { getResponseDataHolder( pResponseData ) };

        // Initialize a new log record
        LogRecord logRecord = getLogRecord( pLevel, pLogId );
        logRecord.setParameters( parameters );

        // Log the event
        java.util.logging.Logger.getLogger( logRecord.getSourceClassName() ).log( logRecord );
    }

    /**
     * Common code for event processing.
     */
    protected static void processEvent( SharedLevel pLevel, int pLogId, Throwable pThrowable, ResponseData pResponseData ) {

        // Correct the type of exception to AccessException if needed, only present with Container Managed Security
        if( pThrowable instanceof java.rmi.AccessException ) {

            // Correct the reason code
            pResponseData.setReasonCode( SecurityReasonCodeConstants.ACCESS_DENIED, SharedConstants.SHARED_SECURITY_APPLICATION );

            // Prepare for the reason code subtext
            ArrayList reasonCodeSubtext = new ArrayList();

            // Try to extract the EJB name and method from the error message
            try {
                String errMsg = pThrowable.getMessage();
                int l1 = errMsg.indexOf( "ejb=" );
                int l2 = errMsg.indexOf( ", methodInterface", l1 );
                reasonCodeSubtext.add( errMsg.substring( l1, l2 ) );

            // Use generic text if not possible to find the needed information
            } catch( Exception exception ) {
                reasonCodeSubtext.add( "this function" );
            }

            // Set the reason code subtext
            pResponseData.setReasonCodeSubText( reasonCodeSubtext );

        }

        // Create additional parameters for the log record
        Object[] parameters = new Object[] { getResponseDataHolder( pResponseData ) };

        // Initialize a new log record
        LogRecord logRecord = getLogRecord( pLevel, pLogId );
        logRecord.setParameters( parameters );
        logRecord.setThrown( pThrowable );

        // Log the event
        java.util.logging.Logger.getLogger( logRecord.getSourceClassName() ).log( logRecord );
    }

    /**
     * Creates and initializase a new log record.
     */
    protected static LogRecord getLogRecord( SharedLevel pLevel, int pLogId ) {

        // Aux vars
        int i = 0;
        String className = null;

        // Get the stack trace
        StackTraceElement stackTraceElement = null;
        StackTraceElement[] stackTrace = ( new Throwable() ).getStackTrace();

        // Get the location
        for( i=0; i<stackTrace.length; i++ ) {
            stackTraceElement = stackTrace[ i ];
            className = stackTrace[ i ].getClassName();
            if( className.equals( SharedException.class.getName() ) ) continue;
            break;
        }

        // Create the new log record
        LogRecord logRecord = new LogRecord( pLevel, "LOG_ID_" + pLogId + " from " + stackTraceElement.toString() );

        // Set parameters for the log record
        logRecord.setSourceClassName( stackTraceElement.getClassName() );
        logRecord.setSourceMethodName( stackTraceElement.getMethodName() );

        // Set the logger name using the agent id if present
        logRecord.setLoggerName( "anonymous" );
        SharedSecurityParam securityParam = (SharedSecurityParam) SecurityLocalThreadContext.getSecurityParam();
        if( securityParam != null ) logRecord.setLoggerName( securityParam.getAgentId() );

        // Send it back
        return( logRecord );
    }

    /**
     * Copies ResponseData to ResponseDataHolder.
     */
    protected static ResponseDataHolder getResponseDataHolder( ResponseData pResponseData ) {

        // Holder for the ResponseData
        ResponseDataHolder responseDataHolder = new ResponseDataHolder();

        // Set the status as a string
        if( pResponseData.isStatusSuccessful() )
            responseDataHolder.setStatus( "Successful" );
        else if( pResponseData.isStatusNonFatal() )
            responseDataHolder.setStatus( "Non Fatal" );
        else if( pResponseData.isStatusFatalReadOnly() )
            responseDataHolder.setStatus( "Fatal Read Only" );
        else if( pResponseData.isStatusFatalWrite() )
            responseDataHolder.setStatus( "Fatal Write" );
        else
            responseDataHolder.setStatus( "NOT SET" );

        // Set the rest of values needed by the logger
        responseDataHolder.setReasonCode( pResponseData.getReasonCode() );
        responseDataHolder.setReasonCodeApplication( pResponseData.getReasonCodeApplication() );
        SharedSecurityParam aSharedSecurityParam = SecurityLocalThreadContext.getSecurityParam();
        responseDataHolder.setReasonCodeMessage( pResponseData.getReasonCodeMessage(aSharedSecurityParam) );
        responseDataHolder.setDebugMessage( pResponseData.getDebugMessage() );
        responseDataHolder.setReasonCodeSubText( pResponseData.getReasonCodeSubText() );

        // Return the ResponseData holder
        return( responseDataHolder );
    }

    /**
     * Creates a fake ResponseDataHolder without actual ResponseData information.
     */
    protected static ResponseDataHolder getResponseDataHolder( String pMessage ) {

        // Holder for the ResponseData
        ResponseDataHolder responseDataHolder = new ResponseDataHolder();

        // Enter only meaningful data this object will contain
        responseDataHolder.setMessage( pMessage );

        // Return the ResponseData holder
        return( responseDataHolder );
    }
}
