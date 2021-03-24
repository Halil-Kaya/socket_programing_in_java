import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public ServerSocket socket;
    public int port;
    public ListenThread listenThread;

    public Server(int port){
        try {

            this.port = port;
            this.socket = new ServerSocket(this.port);
            this.listenThread = new ListenThread(this.socket);

        }catch (Exception e){
            System.out.println("error: " + e.getMessage());
        }

    }

    public void listen(){

        this.listenThread.start();

    }



    class ListenThread extends Thread{

        private ServerSocket socket;

        public ListenThread(ServerSocket socket){

            this.socket = socket;

        }


        @Override
        public void run() {

            while (!this.socket.isClosed()) {

                try{

                    System.out.println("listining");
                    Socket nScoket =this.socket.accept();//blocking method | soket dondurur ip + port
                    SClient nClient = new SClient(nScoket);
                    nClient.listen();

                    /*
                    System.out.println("client connected");

                    ObjectOutputStream cOutput = new ObjectOutputStream(nScoket.getOutputStream());
                    ObjectInputStream cInput = new ObjectInputStream(nScoket.getInputStream());

                    Object msg = cInput.readObject(); //blocking method | waiting message
                    System.out.println(msg.toString());

                    cOutput.writeObject("merhaba hosgeldin");
                    */

                }catch (Exception e){
                    System.out.println("error: " + e.getMessage());
                }


            }

        }
    }

}
