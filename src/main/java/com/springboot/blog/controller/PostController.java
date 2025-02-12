package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
@Tag(
        name = "CRUD REST APIs for Post Resource"
)
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(
            summary = "Create Post REST API",
            description = "Create Post REST API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bearer Authentication" // Pass the scheme name
    )

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }


    @Operation(
            summary = "Get all Posts REST API",
            description = "Get all Posts REST API is used to get all the post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("/api/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return new ResponseEntity<>(postService.getAllPosts(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
    }


    @Operation(
            summary = "Get Post by Id REST API",
            description = "Get Post by Id REST API is used to get a single post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("/api/posts/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable long id) {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }


    @Operation(
            summary = "Update Post REST API",
            description = "Update Post REST API is used to update an existing post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @SecurityRequirement(
            name = "Bearer Authentication" // Pass the scheme name
    )

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/api/posts/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable long id, @Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.updatePost(id, postDto), HttpStatus.OK);
    }

    @Operation(
            summary = "Delete Post REST API",
            description = "Delete Post REST API is used to delete a particular post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @SecurityRequirement(
            name = "Bearer Authentication" // Pass the scheme name
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id) {
        postService.deletePost(id);

        return new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);
    }

    @Operation(
            summary = "Get all Posts by category Id REST API",
            description = "Get all Posts by category Id REST API is used to retrieve all the posts " +
                    "from the database by providing a category Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )

    @GetMapping("/api/posts/category/{id}")
    public ResponseEntity<List<PostDto>> getPostsByCategoryId(@PathVariable("id") Long categoryId) {
        return new ResponseEntity<>(postService.getPostsByCategory(categoryId), HttpStatus.OK);
    }

}
