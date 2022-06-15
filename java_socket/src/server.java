import java.io.IOException;
import java.net.*;

public class server {

    private DatagramSocket datagramSocket;
    private byte[] buffer=new byte[256];

    public server(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    public void receiveThenSend(){
        while(true){
            try{
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
               //InetAddress inetAddress = datagramPacket.getAddress();
                InetAddress inetAddress = datagramPacket.getAddress();
                int port = datagramPacket.getPort();
                String clientmessage = new String(datagramPacket.getData(), 0,datagramPacket.getLength());
                System.out.println("Client Message:"+clientmessage);
                datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, port);
                datagramSocket.send(datagramPacket);

            }catch(IOException e){
                e.printStackTrace();
                break;
            }
        }

    }
    public static void main(String[] args) throws SocketException {
        DatagramSocket datagramSocket = new DatagramSocket(1234);
        server server = new server(datagramSocket);
        server.receiveThenSend();
    }

}
