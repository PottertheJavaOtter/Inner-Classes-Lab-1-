package io.minlee;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by minlee on 5/17/16.
 */
public class ConnectionManagerSpec {

    @Test
    public void getConnectionWithIPAndProtocolTest(){
        ConnectionManager connectionManager = new ConnectionManager(3);
        Connection ftpConnection = connectionManager.getConnection("100.100.100.100","FTP");
        String expectedIp = "100.100.100.100";
        int expectedPort = 20;
        String expectedProtocol = "FTP";
        String actualIP = ftpConnection.getIP();
        int actualPort = ftpConnection.getPort();
        String actualProtocol = ftpConnection.getProtocol();
        assertEquals(expectedIp,actualIP);
        assertEquals(expectedPort,actualPort);
        assertEquals(expectedProtocol,actualProtocol);
    }
    @Test
    public void getConnectionWithIPAndPortTest(){
        ConnectionManager connectionManager = new ConnectionManager(3);
        Connection httpConnection = connectionManager.getConnection("200.200.200.200",80);
        String expectedIp = "200.200.200.200";
        int expectedPort = 80;
        String expectedProtocol = "HTTP";
        String actualIP = httpConnection.getIP();
        int actualPort = httpConnection.getPort();
        String actualProtocol = httpConnection.getProtocol();
        assertEquals(expectedIp,actualIP);
        assertEquals(expectedPort,actualPort);
        assertEquals(expectedProtocol,actualProtocol);
    }
    @Test
    public void getConnectionWithCustomTest(){
        ConnectionManager connectionManager = new ConnectionManager(3);
        Connection customConnection = connectionManager.getConnection("300.300.300.300","WIFI", 10);
        String expectedIp = "300.300.300.300";
        int expectedPort = 10;
        String expectedProtocol = "WIFI";
        String actualIP = customConnection.getIP();
        int actualPort = customConnection.getPort();
        String actualProtocol = customConnection.getProtocol();
        assertEquals(expectedIp,actualIP);
        assertEquals(expectedPort,actualPort);
        assertEquals(expectedProtocol,actualProtocol);
    }
    @Test
    public void getConnectionMaxConnectionsTest(){
        ConnectionManager connectionManager = new ConnectionManager(3);
        Connection ftpConnection = connectionManager.getConnection("100.100.100.100","FTP");
        Connection httpConnection = connectionManager.getConnection("200.200.200.200",80);
        Connection customConnection = connectionManager.getConnection("300.300.300.300","WIFI", 10);
        assertNull(connectionManager.getConnection("400.400.400.400","TELNET"));
    }
    @Test
    public void closeConnectionTest(){
        ConnectionManager connectionManager = new ConnectionManager(3);
        Connection ftpConnection = connectionManager.getConnection("100.100.100.100","FTP");
        Connection httpConnection = connectionManager.getConnection("200.200.200.200",80);
        Connection customConnection = connectionManager.getConnection("300.300.300.300","WIFI", 10);
        String expectedIp = "Error - connection close";
        ftpConnection.close();
        int expectedPort = 0;
        String expectedProtocol = "Error - connection close";
        String actualIP = ftpConnection.getIP();
        int actualPort = ftpConnection.getPort();
        String actualProtocol = ftpConnection.getProtocol();
        assertEquals(expectedIp,actualIP);
        assertEquals(expectedPort,actualPort);
        assertEquals(expectedProtocol,actualProtocol);
        assertNotNull(connectionManager.getConnection("400.400.400.400","TELNET"));
    }
}
