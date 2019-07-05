
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import javax.swing.JOptionPane;

public class Cliente {
    public static void main(String[] args) {
        try{
            DatagramSocket socket = new DatagramSocket();
            DatagramPacket pacote = null;
            String mensagem = "";
            do{
            //ENVIO
                mensagem = JOptionPane.showInputDialog(null, "Digite a Mensagem : ","Cliente Enviando Mensagem",
                                                       JOptionPane.INFORMATION_MESSAGE);
            byte[] msn = mensagem.getBytes();
            pacote = new DatagramPacket(msn,msn.length, InetAddress.getByName("127.0.0.1"), 1100);
            socket.send(pacote);
            System.out.println("Mensagem Enviada!");
            //RECEBIMENTO
            msn = new byte[50];    
            pacote = new DatagramPacket(msn,msn.length);
            socket.receive(pacote);
            System.out.println("Mensagem : " + new String(pacote.getData()));
            
            }while(!mensagem.trim().equals("sair"));
            
        }catch(SocketException e){
            System.out.println("Erro na conexão com socket: " + e.getMessage());
        }catch(IOException erro){
            System.out.println("Erro no pacote ! : " + erro.getMessage());
        }
    }
}
