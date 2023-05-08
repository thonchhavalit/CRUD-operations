package com.example.crudoperation.model.request;



import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserRequest {
    private int UserID;
    @NotBlank(message = "username can't auto fill !!")
    private String username;
    private String name;
    @NotBlank(message = "Address can't auto fill !")
    private String address;
    @NotBlank(message = "gender can't auto fill !")
    private String gender;
}
