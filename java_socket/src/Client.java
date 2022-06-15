import java.io.IOException;
import java.net.*;
import java.util.Scanner;

import static java.net.DatagramSocket.*;

public class Client {
    private DatagramSocket datagramSocket;
    private InetAddress inetAddress;
    private byte[] buffer;

    public Client(DatagramSocket datagramSocket, InetAddress inetAddress) {
        this.datagramSocket = datagramSocket;
        this.inetAddress = inetAddress;
    }
    public void sendThenReceive(){

        Scanner scanner = new Scanner(System.in);
        while(true){
            try{
                String sendMessage = scanner.nextLine();
                buffer = sendMessage.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length,inetAddress,1234);
                datagramSocket.send(datagramPacket);
                datagramSocket.receive(datagramPacket);
                String serverMessage = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println("Server Message: "+serverMessage );
            }catch(IOException e){
                e.printStackTrace();
                break;
            }
        }
    }
    public static void main(String[] args) throws SocketException, UnknownHostException {

        DatagramSocket datagramSocket = new DatagramSocket();
        //InetAddress inetAddress = InetAddress.getAllByName("localhost");
        InetAddress inetAddress =  InetAddress.getByName("localhost");
        Client client = new Client(datagramSocket, inetAddress);
        System.out.println("Send datagram packet to a server.");
        client.sendThenReceive();
    }
}
