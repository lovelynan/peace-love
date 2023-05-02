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
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.io.IOException;
import java.net.InetAddress;
import java.util.*;

public class Service2 extends Service2Grpc.Service2ImplBase {
    public List<Booking> bookings = new ArrayList<>();
    public Map<String, Room> rooms = new HashMap<>();
    Booking newBooking = new Booking();
    HashSet hs = new HashSet();

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

        Booking booking1 = new Booking("1", "1", "9:00", "10:00");
        bookings.add(booking1);
        hs.add(booking1);
    }

    //Implement methods
    @Override
    public void getAvailableRooms(RoomRequest request, StreamObserver<RoomList> responseObserver) {
        String floor = request.getFloor();
        System.out.println("Get Available Rooms.");
        RoomList.Builder response = RoomList.newBuilder();
        if (floor.equals("") || !floor.matches("\\d+") || ((Integer.parseInt(floor) < 0 || Integer.parseInt(floor) > 3))) {
            System.out.println("please enter valid floor:(1-3)");
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
        String regx = "^(?:[9]|1\\d|2[0-2]):[0-5]\\d$";

        // Check if room is available during the requested time
        boolean booked = true;
        do {
            if (roomId.equals("") || !roomId.matches("\\d+") || (Integer.parseInt(roomId) < 1 || Integer.parseInt(roomId) > 10)) {
                System.out.println("please enter valid room id:(1-10)");
                booked = false;
            } else if (userId.equals("") || !userId.matches("\\d+") || (Integer.parseInt(userId) < 1 || Integer.parseInt(userId) > 30)) {
                System.out.println("please enter valid user id:(1-30)");
                booked = false;
            } else if (startTime.equals("") || !startTime.matches(regx)) {
                System.out.println("Please enter valid start time(9:00-21:59):)");
                booked = false;
            } else if (endTime.equals("") || !endTime.matches(regx)) {
                System.out.println("Please enter valid end time(9:00-21:59):)");
                booked = false;
            } else if (Integer.parseInt(startTime.split(":")[0]) > Integer.parseInt(endTime.split(":")[0])
                || (Integer.parseInt(startTime.split(":")[0])==Integer.parseInt(endTime.split(":")[0])
                && Integer.parseInt(startTime.split(":")[1]) >= Integer.parseInt(endTime.split(":")[1]))){
                System.out.println("Start time should be less than end time.");
                booked = false;
            } else {
                //check the booking list, if exists
                for(int i = 0;i<bookings.size();i++) {
                    if ((roomId.equals(bookings.get(i).getRoomId())
                        && Integer.parseInt(startTime.split(":")[0]+startTime.split(":")[1]) < Integer.parseInt(bookings.get(i).getEndTime().split(":")[0]+bookings.get(i).getEndTime().split(":")[1])
                        && Integer.parseInt(endTime.split(":")[0]+endTime.split(":")[1]) > Integer.parseInt(bookings.get(i).getStartTime().split(":")[0]+bookings.get(i).getStartTime().split(":")[1]))
                       // || (roomId.equals(bookings.get(i).getRoomId())&&userId.equals(bookings.get(i).getUserId())
                       // &&startTime.equals(bookings.get(i).getStartTime())&&endTime.equals(bookings.get(i).getEndTime()))
                        || !hs.add(newBooking)) {
                        System.out.println("Has been booked");
                        booked = true;
                        break;
                    }//if
                }//for
                if (booked){
                    // if doesn't exists, book the room and add into the Booking list
                    Booking newBooking = new Booking(roomId, userId, startTime, endTime);
                    bookings.add(newBooking);
                    BookResponse response = BookResponse.newBuilder().setSuccess(booked).build();
                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
                    //print book list at server side
                    for(int i=0;i<bookings.size();i++){
                        System.out.println("Book room " + bookings.get(i).getRoomId() + " from "+ bookings.get(i).getStartTime() + " to end time "+ bookings.get(i).getEndTime() + " is used by user " + bookings.get(i).getUserId());
                    }
                }
                break;
            }
        }while (booked);

        // set response message:success
        BookResponse response = BookResponse.newBuilder().setSuccess(booked).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

}

    @Override
    public StreamObserver<CancelRequest> cancelRoom(StreamObserver<CancelResponse> responseObserver) {

        return new StreamObserver<CancelRequest>() {
            @Override
            public void onNext(CancelRequest request) {
                System.out.println("----------Cancel booking start---------");
                String roomId = request.getRoomId();
                String userId = request.getUserId();
                if (roomId.equals("") || !roomId.matches("\\d+") || (Integer.parseInt(roomId) < 1 || Integer.parseInt(roomId) > 10)) {
                    CancelResponse response = CancelResponse.newBuilder().setSuccess(false).build();
                    System.out.println("Invalid room id");
                    responseObserver.onNext(response);

                } else if (userId.equals("") || !userId.matches("\\d+") || (Integer.parseInt(userId) < 1 || Integer.parseInt(userId) > 30)) {
                    CancelResponse response = CancelResponse.newBuilder().setSuccess(false).build();
                    System.out.println("Invalid user id.");
                    responseObserver.onNext(response);
                }// Cancel booking for the specified user in the specified room
                else if (!bookings.isEmpty()) {
                    int size1 = bookings.size();
                    System.out.println(size1);

                    for (int i = 0; i < bookings.size(); i++) {
                        if (roomId.equals(bookings.get(i).getRoomId()) && userId.equals(bookings.get(i).getUserId())) {
                            bookings.remove(i);
                        }//if
                    }//for
                    int size2 = bookings.size();
                    System.out.println(size2);
                    if (size1-size2==1) {
                        CancelResponse response = CancelResponse.newBuilder().setSuccess(true).build();
                        System.out.println("Cancel room " + roomId + " for user " + userId + " successfully.");
                        responseObserver.onNext(response);
                    }else if(size1-size2==0){
                        CancelResponse response = CancelResponse.newBuilder().setSuccess(false).build();
                        System.out.println("you don't have booked rooms");
                        responseObserver.onNext(response);
                    }else {}
                } else {
                    CancelResponse response = CancelResponse.newBuilder().setSuccess(false).build();
                    System.out.println("no booked rooms.");
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


