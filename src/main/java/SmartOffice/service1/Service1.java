/*
Date:2023/4/20
Author:Nan Wang
Filename:Service1.java
*/

package SmartOffice.service1;
import SmartOffice.HTTPServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.rmi.RemoteException;


public class Service1 extends Service1Grpc.Service1ImplBase {
    /**
     * Runs the server.
     */
    static int port = 50055;

    public static void main(String[] args) throws IOException {
        Service1 service1 = new Service1();
        Server server;

        try {
            server = ServerBuilder.forPort(port).addService(service1).build().start();
            System.out.println("Service1 started....");
            testJMDNS();
            server.awaitTermination();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HttpServer server1 = HttpServer.create(new InetSocketAddress(50055), 0);
        server1.createContext("/index.html", new MyHandler());
        server1.setExecutor(null); // creates a default executor
        server1.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "status code is 200";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    public static void testJMDNS() {
        try {
            // Create a JmDNS instance
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

            // Register a service
            ServiceInfo serviceInfo = ServiceInfo.create("_http._tcp.local.", "service1", port, "path=index.html");
            jmdns.registerService(serviceInfo);

            // Wait a bit
            Thread.sleep(20000);

            // Unregister all services
            //jmdns.unregisterAllServices();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }



    //Implement methods
    @Override
    public void toggleOn(TurnOnRequest request, StreamObserver<TurnOnResponse> responseObserver){
        // Turn on lights for specified room ID
        String roomID = request.getRoomId();
        String message = "Turned on light for room " + roomID;

        if(roomID.equals("")||!roomID.matches("\\d+")||(Integer.parseInt(roomID)<1 || Integer.parseInt(roomID)>10)){
            // Code to turn on lights for valid id
            System.out.println("invalid id. "+message + " unsuccessfully.");
            TurnOnResponse response = TurnOnResponse.newBuilder().setSuccess(false).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();

        }else {
            System.out.println(message + " successfully.");
            TurnOnResponse response = TurnOnResponse.newBuilder().setSuccess(true).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }



    @Override
    public void toggleOff(TurnOffRequest request, StreamObserver<TurnOffResponse> responseObserver) {
        // Turn off lights for specified room ID
        String roomID = request.getRoomId();
        String message = "Lights turned off for room " + roomID;
        if(roomID.equals("")||!roomID.matches("\\d+")||(Integer.parseInt(roomID)<1 || Integer.parseInt(roomID)>10)) {

            System.out.println("invalid id. Please enter a valid room id (1-10).\t "+message + " unsuccessfully.");
            TurnOffResponse response = TurnOffResponse.newBuilder().setSuccess(false).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();

        }else {
            System.out.println(message + " successfully.");
            TurnOffResponse response = TurnOffResponse.newBuilder().setSuccess(true).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    @Override
    public StreamObserver<SetBrightnessRequest> serBrightness(StreamObserver<SetBrightnessResponse> responseObserver) {
        return new StreamObserver<SetBrightnessRequest>() {
            @Override
            public void onNext(SetBrightnessRequest request) {
                // Set brightness for specified room ID
                String roomID = request.getRoomId();
                String brightness = request.getSetBrightness();
                String message = "Brightness set to " + brightness + " for room " + roomID;

                if(roomID.equals("")||!roomID.matches("\\d+")||(Integer.parseInt(roomID)<1 || Integer.parseInt(roomID)>10)) {
                    System.out.println("invalid id. Please enter a valid room id (1-10).\n "+message + " unsuccessfully.");
                    SetBrightnessResponse response = SetBrightnessResponse.newBuilder().setSuccess(false).build();
                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
                }
                else if(brightness.equals("")||!brightness.matches("\\d+")||(Integer.parseInt(brightness)<0 || Integer.parseInt(brightness)>100)){
                    System.out.println(message);
                    SetBrightnessResponse response = SetBrightnessResponse.newBuilder().setSuccess(false).build();
                    responseObserver.onNext(response);
                    responseObserver.onCompleted();

                }else {
                    System.out.println(message + " successfully.");
                    SetBrightnessResponse response = SetBrightnessResponse.newBuilder().setSuccess(true).build();
                    responseObserver.onNext(response);
                    responseObserver.onCompleted();

                }
            }


            @Override
            public void onError(Throwable t) {
                // Handle errors
                System.out.println("Error!!" + t.getMessage());
            }
            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }

}