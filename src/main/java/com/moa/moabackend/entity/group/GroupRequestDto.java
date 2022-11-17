package com.moa.moabackend.entity.group;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GroupRequestDto {
    private List<String> userName;
}
