package org.shadcn.chatsvc.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.shadcn.chatsvc.entity.ChatMessage;
import org.shadcn.chatsvc.enums.Status;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConversationResponse {
    String userId;
    String fullName;
    Status status;
    String avatar;
    ChatMessage lastMessage;
}