/*
Date:2023/4/30
Author:Nan Wang
Filename:Service3.java
*/

package SmartOffice.service3;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.time.LocalDateTime;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.io.IOException;
import java.net.InetAddress;
import java.time.format.DateTimeFormatter;

public class Service3 extends Service3Grpc.Service3ImplBase{
    /**
     * Runs the server.
     */
    static int port = 50057;

    public static void main(String[] args) {
        Service3 service3 = new Service3();


        Server server;
        try {
            server = ServerBuilder.forPort(port).addService(service3).build().start();
            System.out.println("Service3 started....");
            testJMDNS();
            server.awaitTermination();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testJMDNS() {
        try {
            // Create a JmDNS instance
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

            // Register a service
            ServiceInfo serviceInfo = ServiceInfo.create("_http._tcp.local.", "service3FF", port, "path=index.html");
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

    LocalDateTime dateTime = LocalDateTime.now(); // get the current date and time
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String current_time = dateTime.format(formatter);
    String employeedId;
    @Override
    public void checkIn(CheckInRequest request, StreamObserver<CheckInResponse> responseObserver) {
        System.out.println("check in for emloyee " + employeedId );
        String message = "you have checked in";
        String checkin = current_time;

        CheckInResponse response = CheckInResponse.newBuilder().setMessage(message).setCheckInTime(checkin).build();
        System.out.println("check in for employee" + employeedId);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }



    @Override
    public void checkOut(CheckOutRequest request, StreamObserver<CheckOutResponse> responseObserver) {
        System.out.println("check out for emloyee " + employeedId );
        String message = "you have checked out";
        String checkin = current_time;

        CheckOutResponse response = CheckOutResponse.newBuilder().setMessage(message).setCheckOutTime(checkin).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
