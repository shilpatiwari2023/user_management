package api.payload;

public class UserPayLoad {
    public static String getUserPayload(){
        return "{\"name\" : \"Shilpa\", \"gender\" : \"Female\", \"email\" : \"shilpa123@gmail.com" +
                System.currentTimeMillis() + "@test.com\", \"status\" : \"active\"}";
    }
}
