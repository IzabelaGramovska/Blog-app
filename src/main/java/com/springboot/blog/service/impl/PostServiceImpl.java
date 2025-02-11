package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Category;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Category category = categoryRepository.findById(postDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));

        Post post = mapper.map(postDto, Post.class);
        post.setCategory(category);
        Post newPost = postRepository.save(post);

        return mapper.map(newPost, PostDto.class);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending(); // Create an object of type Sort

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort); // Create Pageable instance
        Page<Post> allPosts = postRepository.findAll(pageable);
        List<Post> listOfPosts = allPosts.getContent(); // Get content for page content
        List<PostDto> content = listOfPosts.stream().map((post) -> mapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();

        postResponse.setContent(content);
        postResponse.setPageNo(allPosts.getNumber());
        postResponse.setPageSize(allPosts.getSize());
        postResponse.setTotalElements(allPosts.getTotalElements());
        postResponse.setTotalPages(allPosts.getTotalPages());
        postResponse.setLast(allPosts.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        return mapper.map(post, PostDto.class) ;
    }

    @Override
    public PostDto updatePost(long id, PostDto postDto){
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        Category category = categoryRepository.findById(postDto.getCategoryId())
                        .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        post.setCategory(category);

        Post updatedPost = postRepository.save(post);

        return mapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(long id) {
        Post foundedPost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(foundedPost);
    }

    @Override
    public List<PostDto> getPostsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        List<Post> posts = postRepository.findByCategoryId(categoryId);

        return posts.stream().map((post) -> mapper.map(post, PostDto.class)).collect(Collectors.toList());
    }
}
