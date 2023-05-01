/*
Date:2023/4/21
Author:Nan Wang
Filename:Service2.java
*/

package SmartOffice.service2;

import SmartOffice.Booking;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.util.Date;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.io.IOException;
import java.net.InetAddress;
import java.util.*;

public class Service2 extends Service2Grpc.Service2ImplBase {
    public List<Booking> bookings = new ArrayList<>();
    public Map<String, Room> rooms = new HashMap<>();

    public Service2() {
        // Initialize rooms and bookings
        rooms.put("1", Room.newBuilder().setRoomId("1").setName("Room 1").setAvailable(true).build());
        rooms.put("2", Room.newBuilder().setRoomId("2").setName("Room 2").setAvailable(false).build());
        rooms.put("3", Room.newBuilder().setRoomId("3").setName("Room 3").setAvailable(true).build());
        rooms.put("4", Room.newBuilder().setRoomId("4").setName("Room 4").setAvailable(false).build());
        rooms.put("5", Room.newBuilder().setRoomId("5").setName("Room 5").setAvailable(true).build());
        rooms.put("6", Room.newBuilder().setRoomId("6").setName("Room 6").setAvailable(false).build());
        rooms.put("7", Room.newBuilder().setRoomId("7").setName("Room 7").setAvailable(true).build());
        rooms.put("8", Room.newBuilder().setRoomId("8").setName("Room 8").setAvailable(false).build());
        rooms.put("9", Room.newBuilder().setRoomId("9").setName("Room 9").setAvailable(true).build());
        rooms.put("10", Room.newBuilder().setRoomId("10").setName("Room 10").setAvailable(false).build());
        Booking booking1 = new Booking("1", "1", "9", "10");

        bookings.add(booking1);
    }

    //Implement methods
    @Override
    public void getAvailableRooms(RoomRequest request, StreamObserver<RoomList> responseObserver) {
        String floor = request.getFloor();
        System.out.println("Get Available Rooms.");
        RoomList.Builder response = RoomList.newBuilder();
        if (floor.equals("") || !floor.matches("\\d+") || ((Integer.parseInt(floor) < 0 || Integer.parseInt(floor) > 3))) {
            System.out.println("Invalid floor");
        } else {
            for (Room room : rooms.values()) {
                if (room.getAvailable()) {
                    response.addRooms(room);
                    System.out.println("Room: " + room.getRoomId() + " is available.");
                }
            }
            if (rooms.isEmpty()) {
                System.out.println("No available rooms.");
            }
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void bookRoom(BookRequest request, StreamObserver<BookResponse> responseObserver) {
        String roomId = request.getRoomId();
        String userId = request.getUserId();
        String startTime = request.getStartTime();
        String endTime = request.getEndTime();

        // Check if room is available during the requested time
        boolean available = true;
        if(roomId.equals("")||!roomId.matches("\\d+")||(Integer.parseInt(roomId) < 1||Integer.parseInt(roomId)>10)) {
            available = false;
            System.out.println("Invalid room id");
        }
        else if (userId.equals("") || !userId.matches("\\d+") || (Integer.parseInt(userId) < 0 || Integer.parseInt(userId) > 10)) {
            available = false;
            System.out.println("Invalid user id.");
            //responseObserver.onNext(response);
        } else if (startTime.equals("") || !startTime.matches("\\d+") || (Integer.parseInt(startTime) < 9 || Integer.parseInt(startTime) > 22)) {
            available = false;
            //BookResponse response = BookResponse.newBuilder().setSuccess(false).build();
            System.out.println("Please enter start time: (9-22)");
            //responseObserver.onNext(response);
        } else if (endTime.equals("") || !endTime.matches("\\d+") || (Integer.parseInt(endTime) < 9 || Integer.parseInt(endTime) > 22)) {
            available = false;
            System.out.println("Please enter start time: (9-22)");
        } else if (Integer.parseInt(startTime) >= Integer.parseInt(endTime)) {
            available = false;
            System.out.println("Start time should be less than end time.");
        }else {

        }
        //check the booking list
        for(int i = 0;i<bookings.size();i++) {
            if (roomId.equals(bookings.get(i).getRoomId()) && (Integer.parseInt(startTime) < Integer.parseInt(bookings.get(i).getEndTime()) && Integer.parseInt(endTime) > Integer.parseInt(bookings.get(i).getStartTime()))) {
                System.out.println("Has been booked");
                available = false;
            }else {
            }
        }

        // Cancel booking
        if (available) {

            // Book the room
            Booking newBooking = new Booking(roomId, userId, startTime, endTime);
            bookings.add(newBooking);
            for(Booking booking:bookings){
                System.out.println("room id: " + roomId + "\tstart time: "+ startTime + "\tend time: "+ endTime + " \tuser id " + userId);
            }
        }
        BookResponse response = BookResponse.newBuilder().setSuccess(available).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

}

    @Override
    public StreamObserver<CancelRequest> cancelRoom(StreamObserver<CancelResponse> responseObserver) {

        return new StreamObserver<CancelRequest>() {
            @Override
            public void onNext(CancelRequest request) {
                String roomId = request.getRoomId();
                String userId = request.getUserId();
                if (roomId.equals("") || !roomId.matches("\\d+") || (Integer.parseInt(roomId) < 1 || Integer.parseInt(roomId) > 10)) {
                    CancelResponse response = CancelResponse.newBuilder().setSuccess(false).build();
                    System.out.println("Invalid room id");
                    responseObserver.onNext(response);

                } else if (userId.equals("") || !userId.matches("\\d+") || (Integer.parseInt(userId) < 0 || Integer.parseInt(userId) > 10)) {
                    CancelResponse response = CancelResponse.newBuilder().setSuccess(false).build();
                    System.out.println("Invalid user id.");
                    responseObserver.onNext(response);
                }// Cancel booking for the specified user in the specified room
                else if (!bookings.isEmpty()) {
                    for (int i = 0; i < bookings.size(); i++) {
                        if (roomId.equals(bookings.get(i).getRoomId()) && userId.equals(bookings.get(i).getUserId())) {
                            bookings.remove(i);
                            CancelResponse response = CancelResponse.newBuilder().setSuccess(true).build();
                            System.out.println("Cancel room " + roomId + "for user " + userId + "successfully.");
                            responseObserver.onNext(response);
                        }
                    }//for
                } else {
                    CancelResponse response = CancelResponse.newBuilder().setSuccess(false).build();
                    System.out.println("Cancel room " + roomId + "for user " + userId + "unsuccessfully.");
                    responseObserver.onNext(response);
                }
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Cancel complete");
            }
        };
    }



    /**
     * Runs the server.
     */
    static int port = 50056;

    public static void main(String[] args) {
        Service2 service2 = new Service2();


        Server server;
        try {
            server = ServerBuilder.forPort(port).addService(service2).build().start();
            System.out.println("Service2 started....");
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
            ServiceInfo serviceInfo = ServiceInfo.create("_http._tcp.local.", "service2", port, "path=index.html");
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
}

