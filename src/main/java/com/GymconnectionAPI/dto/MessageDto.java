package com.GymconnectionAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private Long id;
    private Long senderId;
    private List<Long> receivers;
    private Long groupId;
    private String messageContent;
    private LocalDateTime timestamp;
}
