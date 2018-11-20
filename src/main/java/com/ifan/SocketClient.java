package com.ifan;

import java.io.IOException;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) throws IOException {
        int port = 7000;

        String host = "localhost";

        Socket socket = new Socket(host,port);

    }
}
