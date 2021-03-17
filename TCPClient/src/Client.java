import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    public Socket socket;
    public ObjectInputStream cInput;
    public ObjectOutputStream cOutput;


    public String serverIp;
    public int serverPort;

    public void connect(String serverIp,int port){
        this.serverIp = serverIp;
        this.serverPort = port;
        try{

            this.socket = new Socket(this.serverIp,this.serverPort);

            this.cOutput = new ObjectOutputStream(this.socket.getOutputStream());
            this.cInput = new ObjectInputStream(this.socket.getInputStream());

            this.cOutput.writeObject("hellooo");

            Object msg = cInput.readObject();
            System.out.println(msg.toString());


        }catch (Exception e){
            System.out.println("error: " + e.getMessage());
        }
    }


}










