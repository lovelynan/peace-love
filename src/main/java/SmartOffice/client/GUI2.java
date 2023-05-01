/*
Date:2023/4/21
Author:Nan Wang
Filename:GUI2.java
*/

package SmartOffice.client;
import SmartOffice.GetRequest;
import SmartOffice.service2.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.util.*;

public class GUI2 extends JFrame implements ActionListener {
    static String host = "_http._tcp.local.";// = "localhost";
    static int port;// = 50056;
    static String resolvedIP;


    private JPanel panel;
    private JLabel floorLabel, roomIdLabel, userIdLabel, start_timeLabel, end_timeLabel;
    private JTextField floorField, availableRooms, roomIdField, userIdField, start_timeField, end_timeField, replyField;
    private JButton getAvailableBtn, bookRoomBtn, unbookBtn;


    public GUI2() {
        // Set frame properties
        JFrame frame = new JFrame("Smart Office");
        frame.setLayout(null);
        frame.setSize(400, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Add labels and text fields to panel
        floorLabel = new JLabel("Floor");
        floorField = new JTextField(5);
        availableRooms = new JTextField(5);
        userIdLabel = new JLabel("User Id");
        userIdField = new JTextField(5);
        roomIdLabel = new JLabel("Room ID");
        roomIdField = new JTextField(5);
        getAvailableBtn = new JButton("Get Available Rooms");
        start_timeLabel = new JLabel("Start Time");
        start_timeField = new JTextField(5);
        end_timeLabel = new JLabel("End Time");
        end_timeField = new JTextField(5);
        bookRoomBtn = new JButton("Book Room");
        unbookBtn = new JButton("Unbook");
        replyField = new JTextField("Reply", 5);

        //set location and size
        floorLabel.setBounds(350, 60, 130, 30);
        floorField.setBounds(550, 60, 80, 30);
        getAvailableBtn.setBounds(350, 130, 150, 30);
        availableRooms.setBounds(550, 130, 400, 80);
        roomIdLabel.setBounds(350, 270, 130, 30);
        roomIdField.setBounds(550, 270, 80, 30);
        userIdLabel.setBounds(700, 270, 130, 30);
        userIdField.setBounds(850, 270, 80, 30);
        start_timeLabel.setBounds(350, 410, 130, 30);
        start_timeField.setBounds(550, 410, 130, 30);
        end_timeLabel.setBounds(700, 410, 130, 30);
        end_timeField.setBounds(850, 410, 130, 30);
        bookRoomBtn.setBounds(350,480,150,30);
        unbookBtn.setBounds(700,480,150,30);
        replyField.setBounds(350, 550, 120, 30);
        //add components
        frame.add(floorLabel);
        frame.add(floorField);
        frame.add(availableRooms);
        frame.add(roomIdLabel);
        frame.add(roomIdField);
        frame.add(userIdLabel);
        frame.add(userIdField);
        frame.add(start_timeLabel);
        frame.add(start_timeField);
        frame.add(end_timeLabel);
        frame.add(end_timeField);
        frame.add(getAvailableBtn);
        frame.add(bookRoomBtn);
        frame.add(unbookBtn);
        frame.add(replyField);

        // Add action listeners to buttons
        getAvailableBtn.addActionListener(this);
        bookRoomBtn.addActionListener(this);
        unbookBtn.addActionListener(this);
    }


    private static class Service2Listener implements ServiceListener {
        @Override
        public void serviceAdded(ServiceEvent event) {
            System.out.println("Service added: " + event.getInfo());
        }//serviceAdded

        @Override
        public void serviceRemoved(ServiceEvent event) {
            System.out.println("Service removed: " + event.getInfo());
        }//serviceRemoved

        @SuppressWarnings("deprecation")
        public void serviceResolved(ServiceEvent event) {
            System.out.println("Service resolved: " + event.getInfo());
            ServiceInfo info = event.getInfo();
            port = info.getPort();
            resolvedIP = info.getHostAddress();
            System.out.println("IP Resolved - " + resolvedIP + ":" + port);

            String path = info.getNiceTextString().split("=")[1];

            String url =  "http://localhost:"+port+"/"+path;
            System.out.println(" --- sending request to " + url);

            GetRequest.request(url);
        }
    }//serviceResolved


    //implement method
    @Override
    public void actionPerformed(ActionEvent e) {
        String floor = floorField.getText();
        String roomId = roomIdField.getText();
        String userId = userIdField.getText();
        String start_time = start_timeField.getText();
        String end_time = end_timeField.getText();

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", port).usePlaintext().build();
        Service2Grpc.Service2BlockingStub blockingStub = Service2Grpc.newBlockingStub(channel);
        Service2Grpc.Service2Stub stub = Service2Grpc.newStub(channel);


        if (e.getSource() == getAvailableBtn) {
            System.out.println("-----------Get available rooms start--------");

            if (floor.equals("") || !floor.matches("\\d+") || ((Integer.parseInt(floor) < 0 || Integer.parseInt(floor) > 3))) {
                JOptionPane.showMessageDialog(panel, "Please enter a valid floor (0-3).");
            }else{
                RoomRequest request = RoomRequest.newBuilder().setFloor(floor).build();
                SmartOffice.service2.RoomList response = blockingStub.getAvailableRooms(request);
                availableRooms.setText(response.getRoomsList().toString());

            }

        }
        else if (e.getSource() == bookRoomBtn) {
            System.out.println("----------Book rooms start-------------");
            //blocking server-streaming
            // First creating a request message.

            StreamObserver<BookResponse> bookResponseObserver = new StreamObserver<BookResponse>() {
                @Override
                public void onNext(BookResponse response) {
                    boolean available = true;
                    if (roomId.equals("") || !roomId.matches("\\d+") || (Integer.parseInt(roomId) < 0 || Integer.parseInt(roomId) > 10)){
                        JOptionPane.showMessageDialog(panel, "Please enter valid room id (1-10).");
                        available = false;
                    }else if(userId.equals("") || !userId.matches("\\d+") || (Integer.parseInt(userId) < 0 || Integer.parseInt(userId) > 10)){
                        JOptionPane.showMessageDialog(panel, "Please enter valid user id (1-10).");
                        available = false;
                    }else if(!start_time.matches("\\d+") || Integer.parseInt(start_time)<9 || Integer.parseInt(start_time)>22){
                        JOptionPane.showMessageDialog(panel, "Please enter valid start time from 9 to 22.");
                        available = false;
                    }else if (!end_time.matches("\\d+") || Integer.parseInt(end_time)<9 || Integer.parseInt(end_time)>22) {
                        JOptionPane.showMessageDialog(panel, "Please enter valid end time.");
                        available = false;
                    }else if (Integer.parseInt(start_time) >= Integer.parseInt(end_time)) {
                        JOptionPane.showMessageDialog(panel, "Start time should be less than end time.");
                        available = false;
                    }else{
                    }

                    Service2 service2 = new Service2();
                    for(int i = 0; i<service2.bookings.size(); i++){
                        if (roomId.equals(service2.bookings.get(i).getRoomId()) && (Integer.parseInt(start_time) < Integer.parseInt(service2.bookings.get(i).getEndTime()) && Integer.parseInt(end_time) > Integer.parseInt(service2.bookings.get(i).getStartTime()))) {
                            JOptionPane.showMessageDialog(null,"Has been booked.");
                            available = false;
                        }
                    }

                    if (available){
                        JOptionPane.showMessageDialog(panel,"Book room from " + start_time + " to " + end_time + " for user " + userId + " successfully.");
                        replyField.setText(String.valueOf(response.getSuccess()));
                    }else {
                        replyField.setText(String.valueOf(response.getSuccess()));
                    }

                }

                @Override
                public void onError(Throwable t) {
                    System.out.println(t.getStackTrace());
                }

                @Override
                public void onCompleted() {
                    System.out.println("--------book room complete---------");
                }
            };
            //send message
            BookRequest request = BookRequest.newBuilder().setRoomId(roomId).setUserId(userId).setStartTime(start_time).setEndTime(end_time).build();

            stub.bookRoom(request,bookResponseObserver);


            // as this call is blocking. The client will not proceed until all the messages in stream has been received.
            try {
                // Iterating each message in response when calling remote split RPC method.
                Iterator<BookResponse> responses = blockingStub.bookRoom(request);

                // Client keeps a check on the next message in stream.
                while(responses.hasNext()) {
                    BookResponse bookResponse = responses.next();
                    replyField.setText(String.valueOf(bookResponse.getSuccess()));
                }

            } catch (StatusRuntimeException t) {
                t.printStackTrace();
            }


        } else if (e.getSource() == unbookBtn){
            // Add code to send request to server...
            System.out.println("------Cancel room start---------");
            JOptionPane.showMessageDialog(null,"Please enter room id and user id");
            StreamObserver<CancelResponse> responseObserver = new StreamObserver<CancelResponse>() {

                @Override
                public void onNext(CancelResponse response) {

                    // Validate brightness input
                    Service2 service2 = new Service2();
                    if (roomId.equals("") || !roomId.matches("\\d+") || (Integer.parseInt(roomId) < 1 || Integer.parseInt(roomId) > 10)) {
                        JOptionPane.showMessageDialog(panel, "Please enter a valid room id (1-10).");
                        replyField.setText(String.valueOf(response.getSuccess()));
                    } else if (userId.equals("") || !userId.matches("\\d+") || (Integer.parseInt(userId) < 0 || Integer.parseInt(userId) > 10)) {
                        JOptionPane.showMessageDialog(panel, "Please enter valid user id (0-10).");
                        replyField.setText(String.valueOf(response.getSuccess()));
                    }else if (!service2.bookings.isEmpty()) {
                        for (int i = 0; i < service2.bookings.size(); i++) {
                            if (roomId.equals(service2.bookings.get(i).getRoomId()) && userId.equals(service2.bookings.get(i).getUserId())) {
                                service2.bookings.remove(i);
                                JOptionPane.showMessageDialog(panel,"Cancel room " + roomId + "for user " + userId + "successfully.");
                                replyField.setText(String.valueOf(response.getSuccess()));
                            }
                        }
                    }else {
                            JOptionPane.showMessageDialog(panel, "Cancel room " + roomId + " for user " + userId + " successfully.");
                            replyField.setText(String.valueOf(response.getSuccess()));
                    }
                }

                @Override
                public void onError(Throwable t) {
                    System.out.println(t.getMessage());
                }

                @Override
                public void onCompleted() {
                    System.out.println("-------cancel roon completed---------");
                }
            };

            // Add code to send request to server...
            StreamObserver<CancelRequest> requestObserver = stub.cancelRoom(responseObserver);


            try {
                requestObserver.onNext(CancelRequest.newBuilder().setRoomId(roomId).setUserId(userId).build());


                // Mark the end of requests
                requestObserver.onCompleted();

                // Sleep for a bit before sending the next one.
                Thread.sleep(new Random().nextInt(1000) + 500);

            } catch (RuntimeException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

    }


    public static void main(String[] args) throws RuntimeException {
        testClientJMDNS();
        new SmartOffice.client.GUI2();

    }//main


    public static void testClientJMDNS() {
        try {
            // Create a JmDNS instance
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

            // Add a service listener
            jmdns.addServiceListener(host, new GUI2.Service2Listener());

            // Wait a bit
            Thread.sleep(10000);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
