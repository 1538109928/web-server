import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author jiangjingyu6
 * @date 2020/9/28
 */
public class HttpServer {

    /**
     * 端口号
     */
    private final static int PORT = 8080;

    /**
     * host
     */
    private final static String HOST = "127.0.0.1";

    /**
     * shutdown
     */
    private final static boolean SHUTDOWN = false;

    public void await() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT, 1, InetAddress.getByName(HOST));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        while (!SHUTDOWN) {
            Socket socket;
            InputStream inputStream;
            OutputStream outputStream;
            try {
                socket = serverSocket.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();

                Request request = new Request(inputStream);
                request.parse();

                Response response = new Response(outputStream);
                response.setRequest(request);
                response.sendStaticResource();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
