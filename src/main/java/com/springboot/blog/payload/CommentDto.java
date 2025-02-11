package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        description = "CommentDto Model Information"
)
public class CommentDto {
    private long id;

    @Schema(
            description = "Blog Comment Name"
    )
    @NotEmpty(message = "Name should not be empty or null")
    private String name;

    @Schema(
            description = "Blog Comment User Email"
    )
    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;

    @Schema(
            description = "Blog Comment Body"
    )
    @NotEmpty(message = "Body should not be null or empty")
    @Size(min = 10, message = "Comment body must be minimum 10 characters")
    private String body;
}
