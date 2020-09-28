/**
 * @author jiangjingyu6
 * @date 2020/9/28
 */
public class Application {

    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
        httpServer.await();
    }
}
