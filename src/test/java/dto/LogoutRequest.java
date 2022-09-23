package dto;

public class LogoutRequest {
    public String getToken() {
        return token;
    }

    private final String token;

        public LogoutRequest(String token){
            this.token = token;
        }


}
