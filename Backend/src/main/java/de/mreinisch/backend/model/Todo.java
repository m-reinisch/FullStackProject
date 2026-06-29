package de.mreinisch.backend.model;

import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/** Saves a To-Do
 *
 * @param id of To-Do
 * @param description to do
 * @param status of To-Do
 */
@Document("Todos")
public record Todo(@Id String id,
                   @With String description,
                   @With String status) {
}
