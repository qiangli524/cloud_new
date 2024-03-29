/******************************************************************************
 * $Source: /cvsycloud/cloud/src/com/sitech/basd/sxcloud/util/ssh/SshSession.java,v $
 * $Revision: 1.1 $
 * $Author: taoxue $
 * $Date: 2012/06/08 06:27:13 $
 ******************************************************************************
 * Copyright (c) 2003, Eric Daugherty (http://www.ericdaugherty.com)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright notice,
 *       this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Eric Daugherty nor the names of its
 *       contributors may be used to endorse or promote products derived
 *       from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 * *****************************************************************************
 * For current versions and more information, please visit:
 * http://www.ericdaugherty.com/dev/sshwebproxy
 *
 * or contact the author at:
 * web@ericdaugherty.com
 *****************************************************************************/

package com.sitech.basd.sxcloud.util.ssh;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletContext;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

/**
 * Wrapper class for the HTTPSession class.
 *
 * @author Eric Daugherty
 */
public class SshSession {

    //***************************************************************
    // Constants
    //***************************************************************

    private static final String USER = "User";

    private static final String RESTRICTED_MODE_HOST = "RestrictedModeHost";

    private static final String ERROR_MESSAGE = "ErrorMessage";

    public static final String SSH_CONNECTIONS = "SshConnections";

    //***************************************************************
    // Variables
    //***************************************************************

    /** The HttpSession this class is wrapping. */
    private HttpSession session;

    /** Logger */
    private static final Log log = LogFactory.getLog( ShellChannel.class );

    //***************************************************************
    // Constructor
    //***************************************************************

    /**
     * Helper constructor.  Creates a new SSHWebProxy using
     * the current HttpServletRequest.
     *
     * @param request the current HttpServletRequest
     */
    public SshSession( HttpServletRequest request )
    {
        this( request.getSession() );
    }

    /**
     * Creates a wrapper for the current HttpSession.
     *
     * @param session user's HttpSession
     */
    public SshSession( HttpSession session )
    {
        this.session = session;
    }

    //***************************************************************
    // Parameter Access Methods
    //***************************************************************

    /**
     * Checks to makes sure that a user has logged into the system.
     *
     * @return true if a user is logged in.
     */
    public boolean isValid()
    {
        // If we are in restricted mode, the session is valid.
        if( isRestrictedMode() )
        {
            return true;
        }

        // Otherwise, check for a user.
        String username = (String) session.getAttribute( USER );
        if( log.isDebugEnabled() ) log.debug( "Verifying user is logged in.  Username: " + username );
        if( username == null )
        {
            return false;
        }
        return true;
    }

    /**
     * Returns the name of the current logged-in user,
     * or null if no user is logged in.
     *
     * @return Username, or null.
     */
    public String getUser()
    {
        return (String) session.getAttribute( USER );
    }

    /**
     * Sets a username into the session.
     *
     * @param user
     */
    public void setUser( String user )
    {
        session.setAttribute( USER, user );
    }

    public boolean isRestrictedMode()
    {
        String restrictedModeHost = getRestrictedModeHost();
        if( restrictedModeHost != null && restrictedModeHost.length() > 0 )
        {
            return true;
        }
        return false;
    }

    public String getRestrictedModeHost()
    {
        return (String) session.getAttribute( RESTRICTED_MODE_HOST );
    }

    public void setRestrictedModeHost( String host )
    {
        session.setAttribute( RESTRICTED_MODE_HOST, host );
    }

    public String getErrorMessage()
    {
        return (String) session.getAttribute( ERROR_MESSAGE );
    }

    public void setErrorMessage( String errorMessage )
    {
        session.setAttribute( ERROR_MESSAGE, errorMessage );
    }

    /**
     * Returns the SshConnection for the given connectionInfo.
     * Returns null if the connection does not exist or has been closed.
     *
     * @param connectionInfo the connectionInfo unique to this connection.
     * @return the SshConnection or null if it does not exist or has been closed.
     */
    public synchronized SshConnection getSshConnection( String connectionInfo )
    {
        Map sshConnections = getConnectionMap();
        SshConnection sshConnection = (SshConnection) sshConnections.get( connectionInfo );

        // If it is unknown, or open, return it.
        if( sshConnection == null || sshConnection.isOpen() )
        {
            return sshConnection;
        }
        // If it has been closed, remove it and return null.
        else
        {
            if( log.isDebugEnabled() ) log.debug( connectionInfo + " connection is closed, removing from session." );
            sshConnections.remove( connectionInfo );
            return null;
        }
    }

    /**
     * Stores a new SshConnection in the session.
     *
     * @param sshConnection the connection to store.
     * @return returns false if this SshConnection is a duplicate.
     */
    public synchronized boolean addSshConnection( SshConnection sshConnection )
    {
        String connectionInfo = sshConnection.getConnectionInfo();
        Map sshConnections = getConnectionMap();
        if( sshConnections.containsKey( connectionInfo ) )
        {
            log.warn( "Error Adding new SshConnection. A connection already exists with the same connection info: " + connectionInfo );
            return false;
        }

        sshConnections.put( connectionInfo, sshConnection );
        if( log.isDebugEnabled() ) log.debug( connectionInfo + " connection added to session.");
        return true;
    }

    /**
     * Removes an SshConnection from the session.
     *
     * @param connectionInfo
     */
    public synchronized void removeConnection( String connectionInfo )
    {
        if( log.isDebugEnabled() ) log.debug( connectionInfo + " connection removed from session.");
        Map sshConnections = getConnectionMap();
        sshConnections.remove( connectionInfo );
    }

    /**
     * Returns an Collection for the current connections.
     *
     * @return null if no connections exist, or an Collection for the current
     * connections.
     */
    public Collection getConnections()
    {
        Map connections = getConnectionMap();
        return (connections == null) ? null : connections.values();
    }

    //***************************************************************
    // Private Parameter Access Methods
    //***************************************************************

    /**
     * Removes the ConnectionMap from the Session (actually, the ServletContext).
     */
    void removeConnectionMap()
    {
        ServletContext context = session.getServletContext();
        context.removeAttribute( session.getId() + SSH_CONNECTIONS );
    }

    /**
     * Retrieve the ConnectionMap for this session.  The map is actually stored
     * in the ServletContext so it will be accessible by the SessionCleanup class
     * after the session is destroyed.
     *
     * @return A Map of SshConnections, keyed by the connectionInfo String.
     */
    private Map getConnectionMap()
    {
        ServletContext context = session.getServletContext();
        Map sshConnections = (Map)  context.getAttribute( session.getId() + SSH_CONNECTIONS );

        if( sshConnections == null )
        {
            sshConnections = new HashMap();
            context.setAttribute( session.getId() + SSH_CONNECTIONS, sshConnections );
        }

        return sshConnections;
    }
}
