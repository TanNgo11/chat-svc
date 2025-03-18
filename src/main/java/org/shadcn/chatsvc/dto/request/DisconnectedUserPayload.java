package org.shadcn.chatsvc.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DisconnectedUserPayload {
    String userId;

}
