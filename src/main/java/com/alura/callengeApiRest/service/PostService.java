package com.alura.callengeApiRest.service;

import com.alura.callengeApiRest.model.Post;
import com.alura.callengeApiRest.repository.PostRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    // Inyección de dependencias por constructor
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // Obtener todos los posts
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // Obtener un post por ID
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    // Crear o actualizar un post
    public Post savePost(Post post) {
        return postRepository.save(post);
    }
    // Eliminar un post por ID
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
    public Post crearPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> listarPosts() {
        return postRepository.findAll();
    }
}
