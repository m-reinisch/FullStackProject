package de.mreinisch.backend.dto;

/** Transports a To-Do
 *
 * @param description of To-Do
 * @param status of To-Do
 */
public record TodoDTO(String description,
                      String status) {
}
