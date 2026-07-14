package de.mreinisch.backend.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/** Saves the User
 *
 * @param id of App-User
 * @param username of App-User
 */
@Document("AppUsers")
@Builder
public record AppUser(
        @Id
        String id,
        String username
) {
}
