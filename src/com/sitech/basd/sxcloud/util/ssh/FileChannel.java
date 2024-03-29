/******************************************************************************
 * $Source: /cvsycloud/cloud/src/com/sitech/basd/sxcloud/util/ssh/FileChannel.java,v $
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

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import java.io.*;
import java.util.List;

import com.sitech.basd.sxcloud.util.exception.MySecurityException;
import com.sshtools.j2ssh.SftpClient;
import com.sshtools.j2ssh.SshClient;

/**
 * Provide an implementation of the SshChannel for transfering
 * files over an SshConnection.
 *
 * @author Eric Daugherty
 */
public class FileChannel extends SshChannel implements SshConstants {

    //***************************************************************
    // Variables
    //***************************************************************

    /** The SftpClient */
    private SftpClient sftpClient;

    /** Logger */
    private static final Log log = LogFactory.getLog( FileChannel.class );

    //***************************************************************
    // Constructor
    //***************************************************************

    /**
     * Opens a file transfer session with the server.
     *
     * @param sshConnection the connection to use.
     * @param sshClient the  SSH API client.
     * @throws SshConnectException thrown if there is any error opening
     * the connection.
     */
    public FileChannel( SshConnection sshConnection, SshClient sshClient )
        throws MySecurityException
    {
        super( CHANNEL_TYPE_FILE, sshConnection );

        try
        {
            sftpClient = sshClient.openSftpClient();
        }
        catch (IOException ioException)
        {
            log.warn( "FileChannel constructor failed, IOException occured while setting up channel to : " + sshConnection.getConnectionInfo() + ".  Exception: " + ioException, ioException );
            throw new MySecurityException( "Unable to establish File Connection.  IOExeption occured: " + ioException );
        }

        if( log.isInfoEnabled() ) log.debug( "New FileChannel opened to: " + sshConnection.getConnectionInfo() );
    }

    //***************************************************************
    // SshChannel Methods
    //***************************************************************

    /**
     * Closes the Reader and Writer after the Channel has been closed.
     * This should only be called by the SshConnection
     * class and never directly called from this class.
     */
    public void close()
    {
        // Close Readers and Writers.
        if( log.isInfoEnabled() ) log.debug( "Closing FileChannel connected to: " + sshConnection.getConnectionInfo() );

        try
        {
            sftpClient.quit();
        }
        catch( IOException ioException )
        {
            log.warn( "IOException while closing FileChannel: " + ioException );
        }
    }

    /**
     * Indicates whether this connection is still active.
     *
     * @return true if this connection is still active.
     */
    public boolean isConnected() {
        return !sftpClient.isClosed();
    }

    /**
     * Returns the page that should be used to display this Channel.
     *
     * @return
     */
    public String getPage() {
        return PAGE_FILE_HOME + "?connection=" + sshConnection.getConnectionInfo() + "&channel=" + getChannelId();
    }

    //***************************************************************
    // Public Methods
    //***************************************************************

    /**
     * Returns the present working directory for this channel.
     *
     * @return result of pwd command.
     */
    public String getCurrentDirectory()
    {
        return sftpClient.pwd();
    }

    /**
     * Returns a listing of the current directory.
     *
     * @return a valid list, or null if an error occured.
     */
    public List getCurrentDirectoryListing()throws IOException
    {
//        try
//        {
            return sftpClient.ls();
//        }
//        catch (IOException ioException)
//        {
//            log.info( "Error occured while getting directory listing: " + ioException );
//          
//            return null;
//        }
    }

    /**
     * Change to a different directory.
     *
     * @param directory
     * @return true if successful.
     */
    public boolean changeDirectory( String directory )throws IOException
    {
 
        sftpClient.cd( directory );
        return true;
    }


    /**
     * Downloads a file from the remote server.
     *
     * @param fileName the name of the file to download.
     * @param outputStream the stream to write the downloaded file to.
     * @throws IOException thrown if there is an error reading the file.
     */
    public void downloadFile( String fileName, OutputStream outputStream )
        throws IOException
    {
            sftpClient.get( fileName, outputStream );
    }

    /**
     * Uploads a file to the remote server.
     *
     * @param fileName the file to upload.
     * @param inputStream the file as an InputStream.
     * @throws IOException thrown if there is an error writing the file.
     */
    public void uploadFile( String fileName, InputStream inputStream )
        throws IOException
    {
        sftpClient.put( inputStream, fileName );
    }
}
