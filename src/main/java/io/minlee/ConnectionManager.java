package io.minlee;

import java.util.ArrayList;

/**
 * Created by minlee on 5/17/16.
 */
public class ConnectionManager {

    private int numOfConnections, maxNumOfConnections;
    private ArrayList<Connection> connectionList;

    public ConnectionManager(int maxNumOfConnections){
        numOfConnections = 0;
        this.maxNumOfConnections = maxNumOfConnections;
        connectionList = new ArrayList<Connection>();
    }

    public Connection getConnection(String ip, String protocol){
        Connection newConnection;
        if(numOfConnections < maxNumOfConnections){
            if(protocol == "FTP"){
                numOfConnections++;
                newConnection = new ManagedConnection(ip,protocol,20);
                connectionList.add(newConnection);
                return newConnection;
            }
            if(protocol == "SSH"){
                numOfConnections++;
                newConnection = new ManagedConnection(ip,protocol,22);
                connectionList.add(newConnection);
                return newConnection;
            }
            if(protocol == "TELNET"){
                numOfConnections++;
                newConnection = new ManagedConnection(ip,protocol,23);
                connectionList.add(newConnection);
                return newConnection;
            }
            if(protocol == "SMTP"){
                numOfConnections++;
                newConnection = new ManagedConnection(ip,protocol,25);
                connectionList.add(newConnection);
                return newConnection;
            }
        }
        return null;
    }
    public Connection getConnection(String ip, int port){
        Connection newConnection;
        if(numOfConnections < maxNumOfConnections){
            numOfConnections++;
            newConnection = new ManagedConnection(ip,port);
            connectionList.add(newConnection);
            return newConnection;
        }
        return null;
    }
    public Connection getConnection(String ip, String protocol, int port){
        Connection newConnection;
        if(numOfConnections < maxNumOfConnections){
            numOfConnections++;
            newConnection = new ManagedConnection(ip,protocol,port);
            connectionList.add(newConnection);
            return newConnection;
        }
        return null;
    }

    private class ManagedConnection implements Connection{

        private String ip, protocol;
        private int port;

        public ManagedConnection(String ip, int port){
            this.ip = ip;
            protocol = "HTTP";
            this.port = port;
        }
        public ManagedConnection(String ip, String protocol, int port){
            this.ip = ip;
            this.protocol = protocol;
            this.port = port;
        }
        public String connect() {
            return "connected";
        }

        public String getIP() {
            return ip;
        }

        public void setIP(String ip) {
            this.ip = ip;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getProtocol() {
            return protocol;
        }

        public void setProtocol() {

        }
    }
}
