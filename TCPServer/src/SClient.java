import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SClient {

    public Socket socket;

    ObjectOutputStream cOutput;
    ObjectInputStream cInput;
    ClientListenThread listenThread;

    public SClient(Socket socket){
        this.socket = socket;

        try{
            this.cOutput = new ObjectOutputStream(this.socket.getOutputStream());
            this.cInput = new ObjectInputStream(this.socket.getInputStream());
            this.listenThread = new ClientListenThread(this);

        }catch (Exception e){

        }

    }

    public void sendMessage(Object msg){

        if(this.socket.isConnected()){

            try{

                cOutput.writeObject(msg);

            }catch(Exception e){

            }

        }

    }

    public void listen(){

        this.listenThread.start();

    }


    class ClientListenThread extends Thread{

        private SClient client;

        public ClientListenThread(SClient client){

            this.client = client;

        }


        @Override
        public void run() {

            while(!this.client.socket.isClosed()){

                try{

                    Object msg = this.client.cInput.readObject();//blocking method | waiting message
                    System.out.println(msg.toString());

                }catch (Exception e){

                }

            }

        }
    }

}
