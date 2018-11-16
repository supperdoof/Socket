package com.ifan;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    public static void main(String[] args) throws IOException {
        int port = 7000;

        ServerSocket serverSocket = new ServerSocket(port);

        //ServerSocket的accept()方法从连接请求队列中取出一个客户的连接请求
        Socket socket = serverSocket.accept();

        //DataInputStream数据输入流允许应用程序以与机器无关方式从底层输入流中读取基本 Java 数据类型。
        //BufferedInputStream 是缓冲输入流
        //getInputStream方法可以得到一个输入流 getOutputStream方法得到的是一个输出流
        DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

        do{
            double length = dis.readDouble();
            System.out.println("从客户端得到的长度:"+length);
            double area = length * length;
            dos.writeDouble(area);
            //java在使用流时,都会有一个缓冲区,按一种它认为比较高效的方法来发数据:把要发的数据先放到缓冲区,缓冲区放满以后再一次性发过去,而不是分开一次一次地发.
            //而flush()表示强制将缓冲区中的数据发送出去,不必等到缓冲区满.
            dos.flush();
        }while (dis.readInt() != 0);

        //close-----关闭本进程的socket id，但链接还是开着的，用这个socket id的其它进程还能用这个链接，能读或写这个socket id
        socket.close();
        //ServerSocket的close()方法使服务器释放占用的端口，并且断开与所有客户的连接。
        serverSocket.close();
    }
}
