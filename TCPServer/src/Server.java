import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public ServerSocket socket;
    public int port;

    public Server(int port){
        try {

            this.port = port;
            this.socket = new ServerSocket(this.port);
            listen();
        }catch (Exception e){
            System.out.println("error: " + e.getMessage());
        }

    }

    public void listen(){
        System.out.println("listining");
        while (!this.socket.isClosed()){

            try{

                    Socket nClient =this.socket.accept();
                    System.out.println("client connected");

                    ObjectOutputStream cOutput = new ObjectOutputStream(nClient.getOutputStream());
                    ObjectInputStream cInput = new ObjectInputStream(nClient.getInputStream());

                    Object msg = cInput.readObject();
                    System.out.println(msg.toString());

                    cOutput.writeObject("merhaba hosgeldin");


            }catch (Exception e){
                System.out.println("error: " + e.getMessage());
            }

        }

    }

}
