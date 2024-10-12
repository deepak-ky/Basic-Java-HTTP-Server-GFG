import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;


public class SimpleHttpServer {
  public static void main(String[] args) throws IOException {
    // Create an HttpServer instance
    HttpServer httpServer = HttpServer.create(new InetSocketAddress(8000), 0);

    // Create a context for a specific path and set a handler
    httpServer.createContext("/", new MyHandler());

    // Start the server
    httpServer.setExecutor(null);
    httpServer.start();

    System.out.println("Server is running on port 8000");
  }

  static class MyHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
      // handle the request
      String response = "Hello, this is a simple HTTP server response!";
      exchange.sendResponseHeaders(200, response.length());
      OutputStream os = exchange.getResponseBody();
      os.write(response.getBytes());
      os.close();
    }
  }
}
