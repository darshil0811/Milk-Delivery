package milkdelivery.mohit.com.mdapp.model.properties.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

@SerializedName("status")
@Expose
private int status;
@SerializedName("id")
@Expose
private int id;
@SerializedName("message")
@Expose
private String message;

public int getStatus() {
return status;
}

public void setStatus(int status) {
this.status = status;
}

public int getId() {
return id;
}

public void setId(int id) {
this.id = id;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

}