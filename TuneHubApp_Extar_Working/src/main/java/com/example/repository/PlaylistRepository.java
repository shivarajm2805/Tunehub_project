package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.PlayList;

public interface PlaylistRepository extends JpaRepository<PlayList, Integer>
{

}
