import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import javax.swing.JOptionPane;

public class Servidor {
    public static void main(String[] args) {
        try{
            DatagramSocket socket = new DatagramSocket(1100);
            System.out.println("Servidor Iniciado");
            DatagramPacket pacote = null;
            String mensagemTexto = "";
            do{
            //RECEBIMENTO
            byte[] mensagem = new byte[50];    
            pacote = new DatagramPacket(mensagem,mensagem.length);
            socket.receive(pacote);
            System.out.println("Mensagem : " + new String(pacote.getData()));
            //ENVIO
            mensagemTexto = JOptionPane.showInputDialog(null, "Digite a Mensagem : ","Servidor - Enviando Mensagem",
                                                       JOptionPane.INFORMATION_MESSAGE);
            mensagem = mensagemTexto.getBytes();
            pacote = new DatagramPacket(mensagem,mensagem.length,
                                                       InetAddress.getByName("127.0.0.1"), 1100);
            socket.send(pacote);
            System.out.println("Mensagem Enviada!");
            }while(!new String(pacote.getData()).trim().equals("sair"));
            
        }catch(SocketException e){
            System.out.println("Erro ao conectar socket ! : " + e.getMessage());
        }catch(IOException erro){
            System.out.println("Erro no pacote ! : " + erro.getMessage());
        }
    }
}
