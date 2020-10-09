import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * <P>
 *     http 请求体解析
 * </P>
 * @author jiangjingyu6
 * @date 2020/9/28
 */
public class Request {

    private final static int CAPACITY = 2048;

    private InputStream inputStream;

    private String uri;

    public Request(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void parse() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String readLine = reader.readLine();
            String[] req = readLine.split(" ");
            // GET / HTTP/1.1
            uri = req[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUri() {
        return uri;
    }
}
