package com.example.bandera.DataModels.RequestModels;

public class AuthRequestDTO {

    private String email;
    //private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Response DTO
    public static class AuthResponseDTO {
        private String token;

        public AuthResponseDTO(String token) {
            this.token = token;
        }

        // Getter
        public String getToken() {
            return token;
        }
    }
}
