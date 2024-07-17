package edu.quattrinh.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.quattrinh.webservice.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {}
