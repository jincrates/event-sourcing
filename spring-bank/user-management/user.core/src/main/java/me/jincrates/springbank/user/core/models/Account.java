package me.jincrates.springbank.user.core.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String username;
    @Size(min = 4, message= "비밀번호는 4자리 이상이여야합니다.")
    private String password;
    @NotNull(message = "권한은 필수입니다.")
    private List<Role> roles;
}
