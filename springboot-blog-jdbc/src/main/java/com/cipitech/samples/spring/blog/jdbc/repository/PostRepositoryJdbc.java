package com.cipitech.samples.spring.blog.jdbc.repository;

import com.cipitech.samples.spring.blog.jdbc.domain.Post;
import com.cipitech.samples.spring.blog.jdbc.domain.PostStatus;
import com.cipitech.samples.spring.blog.jdbc.exception.SpringBlogException;
import com.cipitech.samples.spring.blog.jdbc.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Profile({"jdbc-plain", "jdbc-boot"})
@Repository
@RequiredArgsConstructor
public class PostRepositoryJdbc implements PostRepository
{
	private final JdbcTemplate jdbcTemplate;
	private final DataSource   dataSource;

	public Post findByPostIDWithoutJdbcTemplate(Integer id)
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try
		{
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("select id, title, description, body, slug, post_status,created_on, updated_on from posts where id=?");
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			Post post = null;
			if (resultSet.next())
			{
				post = Post.builder()
						.id(resultSet.getInt("id"))
						.title(resultSet.getString("title"))
						.description(resultSet.getString("description"))
						.body(resultSet.getString("body"))
						.slug(resultSet.getString("slug"))
						.postStatus(PostStatus.fromString(resultSet.getString("post_status")))
						.createdOn(DateUtils.convertToLocalDateTime(resultSet.getTimestamp("created_on")))
						.updatedOn(DateUtils.convertToLocalDateTime(resultSet.getTimestamp("updated_on")))
						.build();
			}

			return Optional.ofNullable(post).orElseThrow(() -> new SpringBlogException("Cannot find post by id: " + id));
		}
		catch (SQLException e)
		{
			log.error("Error while querying the database", e);
		}
		finally
		{
			if (resultSet != null)
			{
				try
				{
					resultSet.close();
				}
				catch (SQLException e)
				{
					log.error("Error while closing resultSet", e);
				}
			}
			if (statement != null)
			{
				try
				{
					statement.close();
				}
				catch (SQLException e)
				{
					log.error("Error while closing statement", e);
				}
			}
			if (connection != null)
			{
				try
				{
					connection.close();
				}
				catch (SQLException e)
				{
					log.error("Error while closing connection", e);
				}
			}
		}

		return null;
	}

	@Override
	public Post findByPostID(Integer id)
	{
		Post result = jdbcTemplate.queryForObject("select id, title, description, body, slug, post_status,created_on, updated_on from posts where id=?", new PostMapper(), id);
		return Optional.ofNullable(result).orElseThrow(() -> new SpringBlogException("Cannot find post by id: " + id));
	}

	@Override
	public Set<Post> findAllPosts()
	{
		return jdbcTemplate.queryForStream("select id, title, description,body, slug, post_status, created_on, updated_on from posts", new PostMapper()).collect(Collectors.toSet());
	}

	@Override
	public boolean isEmpty()
	{
		Integer count = jdbcTemplate.queryForObject("select COUNT(id) from posts", Integer.class);
		return !(count != null && count > 0);
	}

	@Override
	public boolean postExistsWithTitle(String title)
	{
		Integer count = jdbcTemplate.queryForObject("select COUNT(id) from posts where title=?", Integer.class, title);
		return count != null && count > 0;
	}

	@Override
	public void updatePost(Post post)
	{

	}

	@Override
	public void deletePost(Integer id)
	{

	}

	@Override
	public void addPost(Post post)
	{
		final String sql = "insert into posts(title, description, body, slug, post_status, created_on, updated_on) values (?,?,?,?,?,?,?)";
		jdbcTemplate.update(con ->
		{
			PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, post.getTitle());
			preparedStatement.setString(2, post.getDescription());
			preparedStatement.setString(3, post.getBody());
			preparedStatement.setString(4, post.getSlug());
			preparedStatement.setString(5, PostStatus.intoString(post.getPostStatus()));
			preparedStatement.setTimestamp(6, DateUtils.convertToTimestamp(post.getCreatedOn()));
			preparedStatement.setTimestamp(7, DateUtils.convertToTimestamp(post.getUpdatedOn()));
			return preparedStatement;
		});
	}

	public static class PostMapper implements RowMapper<Post>
	{
		@Override
		public Post mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			Post post = new Post();
			post.setId(rs.getInt("id"));
			post.setTitle(rs.getString("title"));
			post.setDescription(rs.getString("description"));
			post.setBody(rs.getString("body"));
			post.setPostStatus(PostStatus.fromString(rs.getString("post_status")));
			post.setCreatedOn(DateUtils.convertToLocalDateTime(rs.getTimestamp("created_on")));
			post.setUpdatedOn(DateUtils.convertToLocalDateTime(rs.getTimestamp("updated_on")));
			return post;
		}
	}
}
