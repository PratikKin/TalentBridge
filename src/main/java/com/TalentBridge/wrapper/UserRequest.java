package com.TalentBridge.wrapper;

import com.TalentBridge.dto.UserDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest {
    private UserDTO user;
    private String password;

}
