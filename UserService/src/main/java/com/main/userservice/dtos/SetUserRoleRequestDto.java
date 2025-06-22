package com.main.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SetUserRoleRequestDto {
    List<Long> roleIds;
}
