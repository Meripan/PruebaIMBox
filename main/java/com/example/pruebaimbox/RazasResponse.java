package com.example.pruebaimbox;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RazasResponse {

        @SerializedName("status") private String status;
        @SerializedName("message") private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
            return status;
    }

    public void setStatus(String status) {
            this.status = status;
    }

    public String getMessage(){
        return  message;
    }




    @Override
    public String toString() {
        return "RazasResponse{" +
                "message= "+this.message  + "Status " + status +
                '}';
    }
}
