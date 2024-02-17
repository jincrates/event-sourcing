package me.jincrates.springbank.user.core.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "users")
public class User {

    @Id
    private String id;
    
    @NotBlank(message = "이름은 필수입니다.")
    @Size(min = 2, message = "이름은 2 글자 이상이여야합니다.")
    private String name;

    @Valid
    @NotNull(message = "계정 정보는 필수입니다.")
    private Account account;

    public User assignId(String id) {
        this.id = id;
        return this;
    }
}
