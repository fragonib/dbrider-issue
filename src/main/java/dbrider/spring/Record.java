package dbrider.spring;

import java.time.LocalDateTime;
import java.util.UUID;

public record Record(
        UUID id,
        String type,
        LocalDateTime createdOn
) {

}
