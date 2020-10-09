import java.io.*;

/**
 * @author jiangjingyu6
 * @date 2020/9/28
 */
public class Response {

    /**
     * 根目录
     */
    private static final String ROOT = "E:\\Java项目\\web-server\\web\\src\\main\\web\\index.html";

    /**
     * 输出流
     */
    private OutputStream outputStream;

    /**
     * request
     */
    private Request request;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() {
        File file = new File(ROOT + request.getUri());
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                StringBuffer sb = new StringBuffer();
                String line = reader.readLine();
                while (line != null) {
                    sb.append(line).append("\r\n");
                    line = reader.readLine();
                }
                StringBuffer result = new StringBuffer();
                result.append("HTTP/1.1 200 ok \r\n");
                result.append("Content-Language:zh-CN \r\n");
                // charset=UTF-8 解决中文乱码问题
                result.append("Content-Type:text/html;charset=UTF-8 \r\n");
                result.append("Content-Length:" + file.length() + "\r\n");
                result.append("\r\n" + sb.toString());
                outputStream.write(result.toString().getBytes());
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            StringBuffer error = new StringBuffer();
            error.append("HTTP/1.1 200 file not found \r\n");
            error.append("Content-Type:text/html \r\n");
            error.append("Content-Length:20 \r\n").append("\r\n");
            error.append("<h1 >File Not Found..</h1>");
            try {
                outputStream.write(error.toString().getBytes());
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
