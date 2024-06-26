package com.cipitech.samples.spring.plain.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User
{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;

	private String name;
	private String email;
}
