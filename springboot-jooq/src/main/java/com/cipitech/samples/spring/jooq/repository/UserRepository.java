package com.cipitech.samples.spring.jooq.repository;

import com.cipitech.samples.spring.jooq.domain.User;
import com.cipitech.samples.spring.jooq.generated.tables.Users;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository
{
	private final DSLContext dslContext;

	public Optional<User> findUserById(Integer id)
	{
		Record userRecord = dslContext.select()
				.from(Users.USERS)
				.where(Users.USERS.ID.eq(id))
				.fetchOne();

		if(userRecord != null)
		{
			return Optional.of(getUserEntity(userRecord));
		}

		return Optional.empty();
	}

	private User getUserEntity(Record r)
	{
		return User.builder()
				.id(r.getValue(Users.USERS.ID, Integer.class))
				.name(r.getValue(Users.USERS.NAME, String.class))
				.email(r.getValue(Users.USERS.EMAIL, String.class))
				.build();
	}

	public void insertUser(User user)
	{
		dslContext.insertInto(Users.USERS)
				.set(Users.USERS.NAME, user.getName())
				.set(Users.USERS.EMAIL, user.getEmail())
				.returning(Users.USERS.ID)
				.fetchOne();
	}

	public List<User> findAllUsers()
	{
		List<User> users = new ArrayList<>();

		Result<Record> recordResults = dslContext.select().from(Users.USERS).fetch();

		recordResults.forEach(record -> users.add(getUserEntity(record)));

		return users;
	}

	public void deleteUser(Integer userId)
	{
		dslContext.deleteFrom(Users.USERS).where(Users.USERS.ID.equal(userId)).execute();
	}
}
