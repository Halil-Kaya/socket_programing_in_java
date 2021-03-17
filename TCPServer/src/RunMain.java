public class RunMain {
    public static void main(String[] args) {
        Server myServer = new Server(5000);
        myServer.listen();
    }
}
