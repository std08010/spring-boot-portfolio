package com.cipitech.samples.spring.blog.jdbc.repository;

import com.cipitech.samples.spring.blog.jdbc.domain.Post;
import com.cipitech.samples.spring.blog.jdbc.exception.SpringBlogException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Profile("in-memory")
@Repository
public class PostRepositoryInMemory implements PostRepository
{
	private final Set<Post> posts = new CopyOnWriteArraySet<>();

	@Override
	public void addPost(Post post)
	{
		posts.add(post);
	}

	@Override
	public Set<Post> findAllPosts()
	{
		return posts;
	}

	@Override
	public boolean isEmpty()
	{
		return posts.isEmpty();
	}

	@Override
	public Post findByPostID(Integer postId)
	{
		return posts.stream().filter(post -> postId.equals(post.getId())).findFirst()
				.orElseThrow(() -> new SpringBlogException("Cannot find post by id: " + postId));
	}

	@Override
	public boolean postExistsWithTitle(String title)
	{
		return posts.stream().anyMatch(post -> title.equals(post.getTitle()));
	}

	@Override
	public void updatePost(Post post)
	{
		Post savedPost = findByPostID(post.getId());
		post.setId(savedPost.getId());
		posts.add(post);
	}

	@Override
	public void deletePost(Integer id)
	{
		Post onePost = findByPostID(id);
		posts.remove(onePost);
	}
}
