package com.cipitech.samples.spring.blog;

import com.cipitech.samples.spring.blog.domain.Post;
import com.cipitech.samples.spring.blog.domain.PostStatus;
import com.cipitech.samples.spring.blog.repository.PostRepositoryJdbc;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

/**
 * By default, the @JdbcTest or @DataJpaTest annotations will replace
 * any datasource configuration you have in your application.properties
 * with test configuration that can only be applied to an in-memory H2 database.
 * Those annotations work with autoconfiguration either if you have spring-boot-starter-jdbc or jpa in pom.xml or not.
 * So if you want to use the default case you must add the h2 database to your pom.xml with "test" scope.
 * If you don't want to use the default behaviour, and you want to keep your own datasource configuration you must
 * either add the property spring.test.database.replace=none,
 * or you must add the annotation
 * @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
 * AND you must set values to the properties in the spring.datasource family.
 * <p>
 *
 * If you don't set replace=none (only h2 database will be taken into consideration and not the spring.datasource properties)
 * Note: you must also set values to the spring.sql.init properties in order for the database to be created and tables inserted.
 * You must have a h2 database in your pom.xml in order to work.
 * because the JdbcTemplate and the Datasource beans are created automatically,
 * so they will be autowired to PostRepositoryJdbc bean.
 * If you set replace=none (h2 database is not taken into consideration you can either have it in pom.xml or not it doesn't matter)
 * You must add values to the spring.datasource properties in order to work.
 * If you don't add values in spring.datasource then it will not work because
 * the JdbcTemplate and Datasource beans are not created automatically
 * and the JdbcPlainConfig class in not scanned.
 */
@JdbcTest
@Import(PostRepositoryJdbc.class)  // This will only scan the PostRepositoryJdbc bean and create it. As JdbcTemplate and Datasource it will autowire what is created from the @JdbcTest annotation
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostRepositoryJdbcTest
{
	@Autowired
	private PostRepositoryJdbc postRepository;

	@Test
	void testFindAllPosts()
	{
		Post post = new Post();
		post.setTitle("sample blog post");
		post.setDescription("sample blog post");
		post.setBody("sample blog post");
		post.setSlug("sample-blog-post");
		post.setPostStatus(PostStatus.PUBLISHED);
		post.setUpdatedOn(LocalDateTime.now());
		post.setCreatedOn(LocalDateTime.now());
		postRepository.addPost(post);
		//Any data that is inserted in the database during tests is reverted, so it is deleted from the database.

		Assertions.assertThat(postRepository.findAllPosts()).hasSize(1);
	}
}
