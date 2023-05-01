/*
Date:2023/4/21
Author:Nan Wang
Filename:Booking.java
*/

package SmartOffice;

public class Booking {
        private String userId;
        private String startTime;
        private String endTime;
        private String roomId;


        public Booking(String roomId,String userId, String startTime, String endTime) {
            this.userId = userId;
            this.startTime = startTime;
            this.endTime = endTime;
            this.roomId = roomId;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }
}
