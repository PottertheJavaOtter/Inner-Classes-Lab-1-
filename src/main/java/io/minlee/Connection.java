package io.minlee;

/**
 * Created by minlee on 5/17/16.
 */
public interface Connection {

    String connect();

    String getIP();

    void setIP(String ip);

    int getPort();

    void setPort(int port);

    String getProtocol();

    void setProtocol();

}
