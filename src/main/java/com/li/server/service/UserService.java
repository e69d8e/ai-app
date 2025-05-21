package com.li.server.service;

import com.li.common.result.Result;
import com.li.pojo.dto.LoginDTO;
import com.li.pojo.dto.RegisterDTO;
import com.li.pojo.dto.UpdatePasswordDTO;
import com.li.pojo.dto.UserDTO;

public interface UserService {
    Result login(LoginDTO loginDTO);

    Result register(RegisterDTO registerDTO);

    Result updatePassword(UpdatePasswordDTO updatePasswordDTO);

    Result update(UserDTO userDTO);
}
