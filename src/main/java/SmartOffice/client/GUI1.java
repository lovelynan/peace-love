/*
Date:2023/4/20
Author:Nan Wang
Filename:GUI3.java
*/

package SmartOffice.client;

import SmartOffice.GetRequest;
import SmartOffice.service1.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.util.Random;

public class GUI1 extends JFrame implements ActionListener {
    static String host = "_http._tcp.local.";// = "localhost";
    static int port;// = 50055;
    static String resolvedIP;

    private JPanel panel;
    private JLabel roomIdLabel;
    private JTextField roomIdField, brightnessField,replyField;
    private JButton toggleOnBtn, toggleOffBtn, setBrightnessBtn;




    public GUI1(){
        // Set frame properties
        JFrame frame=new JFrame("Smart Office");
        frame.setLayout(null);
        frame.setSize(400, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    // Add labels and text fields to panel
    roomIdLabel = new JLabel("Room ID:");
    roomIdField = new JTextField(5);
    toggleOnBtn = new JButton("Toggle On");
    toggleOffBtn = new JButton("Toggle Off");
    brightnessField = new JTextField(5);
    setBrightnessBtn = new JButton("Set Brightness");
    replyField = new JTextField("Reply",5);
    //set location and size
    roomIdLabel.setBounds(500,200,130,30);
    roomIdField.setBounds(650,200,130,30);
    toggleOnBtn.setBounds(500,270,130,30);
    toggleOffBtn.setBounds(650,270,130,30);
    setBrightnessBtn.setBounds(500,340,130,30);
    brightnessField.setBounds(650,340,130,30);
    replyField.setBounds(500,410,130,30);
    //add components
    frame.add(roomIdLabel);
    frame.add(roomIdField);
    frame.add(brightnessField);
    frame.add(toggleOnBtn);
    frame.add(toggleOffBtn);
    frame.add(setBrightnessBtn);
    frame.add(replyField);

    // Add action listeners to buttons
    toggleOnBtn.addActionListener(this);
    toggleOffBtn.addActionListener(this);
    setBrightnessBtn.addActionListener(this);
}

    private static class Service1Listener implements ServiceListener {
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
            GetRequest.request("http://localhost:"+port+"/"+path);


        }
    }//serviceResolved

    @Override
    public void actionPerformed(ActionEvent e) {
        String roomId = roomIdField.getText();
        String brightness = brightnessField.getText();

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", port).usePlaintext().build();
        Service1Grpc.Service1BlockingStub blockingStub = Service1Grpc.newBlockingStub(channel);
        Service1Grpc.Service1Stub asyncStub;
        asyncStub = Service1Grpc.newStub(channel);


        if (e.getSource() == toggleOnBtn) {
            if(roomId.equals("")||!roomId.matches("\\d+")||(Integer.parseInt(roomId)<1 || Integer.parseInt(roomId)>10)){
                JOptionPane.showMessageDialog(panel,"Please enter a valid room id (1-10).");
                TurnOnRequest request = TurnOnRequest.newBuilder().setRoomId(roomId).setTurnOn(true).build();
                SmartOffice.service1.TurnOnResponse response = blockingStub.toggleOn(request);
                replyField.setText(String.valueOf(response.getSuccess()));
            }else{
            // Call RPC to turn on lights for room
                TurnOnRequest request = TurnOnRequest.newBuilder().setRoomId(roomId).setTurnOn(true).build();
                SmartOffice.service1.TurnOnResponse response = blockingStub.toggleOn(request);
                replyField.setText(String.valueOf(response.getSuccess()));}

            // Add code to send request to server...
            System.out.println("Turn on lights for room " + roomId);
        } else if (e.getSource() == toggleOffBtn) {
            if(roomId.equals("")||!roomId.matches("\\d+")||(Integer.parseInt(roomId)<1 || Integer.parseInt(roomId)>10)){
                JOptionPane.showMessageDialog(panel,"Please enter a valid room id (1-10).");
                TurnOffRequest request = TurnOffRequest.newBuilder().setRoomId(roomId).setTurnOff(true).build();
                SmartOffice.service1.TurnOffResponse response = blockingStub.toggleOff(request);
                replyField.setText(String.valueOf(response.getSuccess()));
            }else {
                // Call RPC to turn off lights for room
                TurnOffRequest request = TurnOffRequest.newBuilder().setRoomId(roomId).setTurnOff(true).build();
                SmartOffice.service1.TurnOffResponse response = blockingStub.toggleOff(request);
                replyField.setText(String.valueOf(response.getSuccess()));

            // Add code to send request to server...
            System.out.println("Turn off lights for room " + roomId);}
        } else if (e.getSource() == setBrightnessBtn) {
            // Call RPC to set brightness for room
            StreamObserver<SetBrightnessResponse> response = new StreamObserver<SetBrightnessResponse>(){

                @Override
                public void onNext(SetBrightnessResponse response) {

                    // Validate brightness input
                    if (roomId.equals("") || !roomId.matches("\\d+") || (Integer.parseInt(roomId) < 1 || Integer.parseInt(roomId) > 10)) {
                        JOptionPane.showMessageDialog(panel,"Please enter a valid room id (1-10).");
                        replyField.setText(String.valueOf(response.getSuccess()));

                    } else if (brightness.equals("") || !brightness.matches("\\d+") || (Integer.parseInt(brightness) < 0 || Integer.parseInt(brightness) > 100)) {
                        JOptionPane.showMessageDialog(panel,"Please enter valid brightness (0-100).");
                        replyField.setText(String.valueOf(response.getSuccess()));
                    } else {
                        JOptionPane.showMessageDialog(panel,"Set brightness to " + brightness + " for room " + roomId + " successfully.");
                        replyField.setText(String.valueOf(response.getSuccess()));
                    }
                }

                @Override
                public void onError(Throwable t) {
                    System.out.println(t.getMessage());
                }

                @Override
                public void onCompleted() {
                    System.out.println("Completed.");
                }
            };

            // Add code to send request to server...
            StreamObserver<SetBrightnessRequest> request = asyncStub.serBrightness(response);


                try {
                    request.onNext(SetBrightnessRequest.newBuilder().setRoomId(roomId).setSetBrightness(brightness).build());


                    // Mark the end of requests
                    request.onCompleted();

                    // Sleep for a bit before sending the next one.
                    Thread.sleep(new Random().nextInt(1000) + 500);

                } catch (RuntimeException ex) {
                    ex.printStackTrace();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            // Add code to send request to server...
            System.out.println("Set brightness to " + brightness + " for room " + roomId);
        }
    }


    public static void main(String[] args) throws RuntimeException {
        testClientJMDNS();
        new SmartOffice.client.GUI1();


    }//main


    public static void testClientJMDNS() {
        try {
            // Create a JmDNS instance
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

            // Add a service listener
            jmdns.addServiceListener(host, new Service1Listener());

            // Wait a bit
            Thread.sleep(10000);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


}

