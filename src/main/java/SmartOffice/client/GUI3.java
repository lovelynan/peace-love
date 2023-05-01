/*
Date:2023/4/23
Author:Nan Wang
Filename:GUI3.java
*/

package SmartOffice.client;
import SmartOffice.GetRequest;
import SmartOffice.service3.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;

public class GUI3 extends JFrame implements ActionListener {
    static String host = "_http._tcp.local.";// = "localhost";
    static int port;// = 50057;
    static String resolvedIP;

    private JButton checkinBtn,checkoutBtn;
    private JLabel employeeIdLablel;
    private JTextField employeeIdField,replyField;

    public GUI3() {
        // Set frame properties
        JFrame frame = new JFrame("Smart Office");
        frame.setLayout(null);
        frame.setSize(400, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Add components
        checkoutBtn = new JButton("check-out ");
        checkinBtn = new JButton("check-in");
        employeeIdField = new JTextField(5);
        replyField = new JTextField( 5);
        employeeIdLablel = new JLabel("employee id");

        //set location and size
        checkinBtn.setBounds(350, 200, 120, 30);
        checkoutBtn.setBounds(550, 200, 120, 30);
        employeeIdLablel.setBounds(350, 250,120,30);
        employeeIdField.setBounds(550,250,80,30);
        replyField.setBounds(350, 350, 250, 100);
        //add components
        frame.add(checkinBtn);
        frame.add(checkoutBtn);
        frame.add(replyField);
        frame.add(employeeIdField);
        frame.add(employeeIdLablel);

        // Add action listeners to buttons
        checkinBtn.addActionListener(this);
        checkoutBtn.addActionListener(this);
    }

    private static class Service3Listener implements ServiceListener {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String employeedId = employeeIdField.getText();
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", port).usePlaintext().build();
        Service3Grpc.Service3BlockingStub blockingStub = Service3Grpc.newBlockingStub(channel);


        if (e.getSource() == checkinBtn){
            System.out.println("check in for emloyee " + employeedId );
            if (employeedId.equals("") || !employeedId.matches("\\d+") || ((Integer.parseInt(employeedId) < 0 || Integer.parseInt(employeedId) > 30))) {
                JOptionPane.showMessageDialog(null, "Please enter a valid employee id:(1-30).");
            }else{
                CheckInRequest request = CheckInRequest.newBuilder().setEmployeeId(employeedId).build();
                SmartOffice.service3.CheckInResponse response = blockingStub.checkIn(request);
                String msg = response.getMessage() + "\n" + response.getCheckInTime();
                replyField.setText(msg);

            }

        }else if (e.getSource() == checkoutBtn){
            System.out.println("check out for emloyee " + employeedId );
            if (employeedId.equals("") || !employeedId.matches("\\d+") || ((Integer.parseInt(employeedId) < 0 || Integer.parseInt(employeedId) > 30))) {
                JOptionPane.showMessageDialog(null, "Please enter a valid employee id:(1-30).");
            }else{
                CheckOutRequest request = CheckOutRequest.newBuilder().setEmployeeId(employeedId).build();
                SmartOffice.service3.CheckOutResponse response = blockingStub.checkOut(request);
                String msg = response.getMessage() + "\n" + response.getCheckOutTime();
                replyField.setText(msg);

            }

        }else {}
    }




    public static void main(String[] args) throws RuntimeException {
        testClientJMDNS();
        new SmartOffice.client.GUI3();

    }//main


    public static void testClientJMDNS() {
        try {
            // Create a JmDNS instance
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

            // Add a service listener
            jmdns.addServiceListener(host, new GUI3.Service3Listener());

            // Wait a bit
            Thread.sleep(10000);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
