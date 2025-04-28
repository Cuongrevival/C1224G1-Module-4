package org.example.repository;

import org.example.model.MusicValidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMusicValidateRepo extends JpaRepository<MusicValidate, Integer> {
}
