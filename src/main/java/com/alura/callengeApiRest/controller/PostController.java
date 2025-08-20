package com.alura.callengeApiRest.controller;
import com.alura.callengeApiRest.model.Post;
import com.alura.callengeApiRest.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    // Inyección de dependencias
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // Obtener todos los posts
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    // Obtener un post por ID
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return postService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo post
    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.savePost(post);
    }

    // Actualizar un post existente
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
        return postService.getPostById(id)
                .map(existingPost -> {
                    existingPost.setTitle(post.getTitle());
                    existingPost.setContent(post.getContent());
                    return ResponseEntity.ok(postService.savePost(existingPost));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un post por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        return postService.getPostById(id)
                .map(post -> {
                    postService.deletePost(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
