package com.cipitech.samples.spring.blog.jpa.repository;

import com.cipitech.samples.spring.blog.jpa.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

//@RepositoryRestResource(path = "/api/data/comments")
@CrossOrigin
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>
{
	boolean existsCommentByTitle(String title);
}
