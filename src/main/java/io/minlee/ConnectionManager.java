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
        private boolean connection;

        public ManagedConnection(String ip, int port){
            this.ip = ip;
            protocol = "HTTP";
            this.port = port;
            connection = true;
        }
        public ManagedConnection(String ip, String protocol, int port){
            this.ip = ip;
            this.protocol = protocol;
            this.port = port;
            connection = true;

        }
        public String connect() {
            if (connection) {
                return "connected";
            }
            return "Error - connection close";
        }

        public String getIP() {
            if (connection) {
                return ip;
            }
            return "Error - connection close";
        }

        public void setIP(String ip) {
            this.ip = ip;
        }

        public int getPort() {
            if (connection) {
                return port;
            }
            return 0;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getProtocol() {
            if (connection) {
                return protocol;
            }
            return "Error - connection close";
        }

        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }
        public void close(){
            connection = false;
            numOfConnections--;
        }
    }
}
